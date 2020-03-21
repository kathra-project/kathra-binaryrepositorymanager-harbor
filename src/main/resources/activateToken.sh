#!/bin/bash

function harborInitFirstConnexion() {
    echo "harborInitFirstConnexion(harborUrl: $1, keycloakHost: $2,userLogin: $3, userPassword: $4)"
    local harborUrl=$1
    local keycloak=$2
    local userLogin=$3
    local userPassword=$4
    local keycloakUrl=https://${keycloak}
    local tmp="/tmp/harborInitFirstConnexion.$(date +%s%N)"
    echo "tmp=$tmp"
    mkdir $tmp

    local UA='user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.80 Safari/537.36'
    local headerAccept='accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3'
    local headerAcceptLang="Accept-Language: fr,fr-FR;q=0.8,en-US;q=0.5,en;q=0.3"

    curl -L -v ${harborUrl}/c/oidc/login  2> $tmp/harbor.login.err > $tmp/harbor.login
    local location=$(getHttpHeaderLocation $tmp/harbor.login.err)
    local cookieSID=$(getHttpHeaderSetCookie $tmp/harbor.login.err ".*")

    curl -v $location -H "authority: ${keycloak}" -H 'upgrade-insecure-requests: 1' -H "$UA" -H 'sec-fetch-mode: navigate' -H 'sec-fetch-site: none' -H "$headerAcceptLang" > $tmp/harbor.authenticate 2> $tmp/harbor.authenticate.err

    local uriLogin=$(grep "action=" < $tmp/harbor.authenticate  | sed "s/.* action=\"\([^\"]*\)\".*/\1/" | sed 's/amp;//g' | tr -d '\r\n')
    local AUTH_SESSION_ID=$(getHttpHeaderSetCookie $tmp/harbor.authenticate.err AUTH_SESSION_ID)
    local KC_RESTART=$(getHttpHeaderSetCookie $tmp/harbor.authenticate.err KC_RESTART)
    local location=$(getHttpHeaderLocation $tmp/harbor.authenticate.err )

    curl -v $uriLogin -H "authority: ${keycloak}" -H 'cache-control: max-age=0' -H "origin: ${keycloakUrl}" -H 'upgrade-insecure-requests: 1' -H 'content-type: application/x-www-form-urlencoded' -H "$UA" -H "$headerAccept" -H "referer: $location" -H "$headerAcceptLang" -H "Cookie:$AUTH_SESSION_ID;$KC_RESTART" --data-urlencode "username=${userLogin}"  --data-urlencode "password=${userPassword}" --compressed 2> $tmp/harbor.kc.post.err > $tmp/harbor.kc.post

    local locationFinishLogin=$(getHttpHeaderLocation $tmp/harbor.kc.post.err )

    curl -v ${locationFinishLogin} -H "$UA" -H "$headerAccept" -H "$headerAcceptLang" --compressed -H "Referer: ${keycloakUrl}/" -H "DNT: 1" -H "Connection: keep-alive" -H "Cookie: $cookieSID; screenResolution=1920x1200" -H "Upgrade-Insecure-Requests: 1" -H "TE: Trailers" 2> $tmp/harbor.finishLogin.err > $tmp/harbor.finishLogin

    grep "HTTP.* 200" < $tmp/harbor.finishLogin.err > /dev/null && echo "User '$userLogin' already declared in Harbor"  && rm -Rf $tmp && return 0
    grep -i "Location: \/[[:space:]]*$" < $tmp/harbor.finishLogin.err  > /dev/null && echo "User '$userLogin' already declared in Harbor" && rm -Rf $tmp && return 0

    grep -i "Location: \/oidc-onboard.*" < $tmp/harbor.finishLogin.err > /dev/null
    [ $? -ne 0 ] && echo "Client should be redirected to /oidc-onboard.*" && return 1

    curl --fail -v ${harborUrl}/c/oidc/onboard -H "$UA" -H "Accept: application/json, text/plain, */*" -H "$headerAcceptLang" --compressed -H "Referer: ${harborUrl}/oidc-onboard?username=" -H "content-type: application/json" -H "DNT: 1" -H "Connection: keep-alive" -H "Cookie: $cookieSID" -H "TE: Trailers" --data "{\"username\": \"$userLogin\"}" 2> $tmp/harbor.defineUserId.err
    [ $? -ne 0 ] && echo "Unable to connect with user $userLogin on harbor.${harborUrl}" && cat $tmp/harbor.defineUserId.err && return 1

    rm -Rf $tmp
    return 0
}
export -f harborInitFirstConnexion

function getHttpHeaderLocation() {
    echo "getHttpHeaderLocation(file: $1)" >&2
    grep -i "< Location" < $1 | sed 's/< Location: //gi' | tr -d '\r\n'
}
export -f getHttpHeaderLocation
function getHttpHeaderSetCookie() {
    echo "getHttpHeaderSetCookie(file: $1, cookie: $2)" >&2
    grep -i "< Set-Cookie" < $1 | grep -i "$2" | sed 's/< Set-Cookie://gi' | sed 's/;.*//g' | tr '\r\n' ';'
}
export -f getHttpHeaderSetCookie

harborInitFirstConnexion "$1" "$2" "$3" "$4" "$5"