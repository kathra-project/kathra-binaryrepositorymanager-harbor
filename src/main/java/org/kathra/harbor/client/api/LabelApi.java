/*
 * Harbor API
 * These APIs provide services for manipulating Harbor project.
 *
 * OpenAPI spec version: 1.10.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package org.kathra.harbor.client.api;

import org.kathra.harbor.client.ApiCallback;
import org.kathra.harbor.client.ApiClient;
import org.kathra.harbor.client.ApiException;
import org.kathra.harbor.client.ApiResponse;
import org.kathra.harbor.client.Configuration;
import org.kathra.harbor.client.Pair;
import org.kathra.harbor.client.ProgressRequestBody;
import org.kathra.harbor.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import org.kathra.harbor.client.model.Label;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LabelApi {
    private ApiClient apiClient;

    public LabelApi() {
        this(Configuration.getDefaultApiClient());
    }

    public LabelApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for chartrepoRepoChartsNameVersionLabelsGet
     * @param repo The project name (required)
     * @param repo The project name (required)
     * @param name The chart name (required)
     * @param version The chart version (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call chartrepoRepoChartsNameVersionLabelsGetCall(String repo, String name, String version, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/chartrepo/{repo}/charts/{name}/{version}/labels"
            .replaceAll("\\{" + "repo" + "\\}", apiClient.escapeString(repo.toString()))
            .replaceAll("\\{" + "name" + "\\}", apiClient.escapeString(name.toString()))
            .replaceAll("\\{" + "version" + "\\}", apiClient.escapeString(version.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json", "text/plain"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "basicAuth" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call chartrepoRepoChartsNameVersionLabelsGetValidateBeforeCall(String repo, String name, String version, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'repo' is set
        if (repo == null) {
            throw new ApiException("Missing the required parameter 'repo' when calling chartrepoRepoChartsNameVersionLabelsGet(Async)");
        }
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new ApiException("Missing the required parameter 'name' when calling chartrepoRepoChartsNameVersionLabelsGet(Async)");
        }
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new ApiException("Missing the required parameter 'version' when calling chartrepoRepoChartsNameVersionLabelsGet(Async)");
        }
        

        com.squareup.okhttp.Call call = chartrepoRepoChartsNameVersionLabelsGetCall(repo, name, version, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Return the attahced labels of chart.
     * Return the attahced labels of the specified chart version.
     * @param repo The project name (required)
     * @param name The chart name (required)
     * @param version The chart version (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void chartrepoRepoChartsNameVersionLabelsGet(String repo, String name, String version) throws ApiException {
        chartrepoRepoChartsNameVersionLabelsGetWithHttpInfo(repo, name, version);
    }

    /**
     * Return the attahced labels of chart.
     * Return the attahced labels of the specified chart version.
     * @param repo The project name (required)
     * @param name The chart name (required)
     * @param version The chart version (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> chartrepoRepoChartsNameVersionLabelsGetWithHttpInfo(String repo, String name, String version) throws ApiException {
        com.squareup.okhttp.Call call = chartrepoRepoChartsNameVersionLabelsGetValidateBeforeCall(repo, name, version, null, null);
        return apiClient.execute(call);
    }

    /**
     * Return the attahced labels of chart. (asynchronously)
     * Return the attahced labels of the specified chart version.
     * @param repo The project name (required)
     * @param name The chart name (required)
     * @param version The chart version (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call chartrepoRepoChartsNameVersionLabelsGetAsync(String repo, String name, String version, final ApiCallback<Void> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = chartrepoRepoChartsNameVersionLabelsGetValidateBeforeCall(repo, name, version, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
    /**
     * Build call for chartrepoRepoChartsNameVersionLabelsIdDelete
     * @param repo The project name (required)
     * @param name The chart name (required)
     * @param version The chart version (required)
     * @param id The label ID (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call chartrepoRepoChartsNameVersionLabelsIdDeleteCall(String repo, String name, String version, Integer id, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/chartrepo/{repo}/charts/{name}/{version}/labels/{id}"
            .replaceAll("\\{" + "repo" + "\\}", apiClient.escapeString(repo.toString()))
            .replaceAll("\\{" + "name" + "\\}", apiClient.escapeString(name.toString()))
            .replaceAll("\\{" + "version" + "\\}", apiClient.escapeString(version.toString()))
            .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json", "text/plain"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "basicAuth" };
        return apiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call chartrepoRepoChartsNameVersionLabelsIdDeleteValidateBeforeCall(String repo, String name, String version, Integer id, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'repo' is set
        if (repo == null) {
            throw new ApiException("Missing the required parameter 'repo' when calling chartrepoRepoChartsNameVersionLabelsIdDelete(Async)");
        }
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new ApiException("Missing the required parameter 'name' when calling chartrepoRepoChartsNameVersionLabelsIdDelete(Async)");
        }
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new ApiException("Missing the required parameter 'version' when calling chartrepoRepoChartsNameVersionLabelsIdDelete(Async)");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling chartrepoRepoChartsNameVersionLabelsIdDelete(Async)");
        }
        

        com.squareup.okhttp.Call call = chartrepoRepoChartsNameVersionLabelsIdDeleteCall(repo, name, version, id, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Remove label from chart.
     * Remove label from the specified chart version.
     * @param repo The project name (required)
     * @param name The chart name (required)
     * @param version The chart version (required)
     * @param id The label ID (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void chartrepoRepoChartsNameVersionLabelsIdDelete(String repo, String name, String version, Integer id) throws ApiException {
        chartrepoRepoChartsNameVersionLabelsIdDeleteWithHttpInfo(repo, name, version, id);
    }

    /**
     * Remove label from chart.
     * Remove label from the specified chart version.
     * @param repo The project name (required)
     * @param name The chart name (required)
     * @param version The chart version (required)
     * @param id The label ID (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> chartrepoRepoChartsNameVersionLabelsIdDeleteWithHttpInfo(String repo, String name, String version, Integer id) throws ApiException {
        com.squareup.okhttp.Call call = chartrepoRepoChartsNameVersionLabelsIdDeleteValidateBeforeCall(repo, name, version, id, null, null);
        return apiClient.execute(call);
    }

    /**
     * Remove label from chart. (asynchronously)
     * Remove label from the specified chart version.
     * @param repo The project name (required)
     * @param name The chart name (required)
     * @param version The chart version (required)
     * @param id The label ID (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call chartrepoRepoChartsNameVersionLabelsIdDeleteAsync(String repo, String name, String version, Integer id, final ApiCallback<Void> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = chartrepoRepoChartsNameVersionLabelsIdDeleteValidateBeforeCall(repo, name, version, id, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
    /**
     * Build call for chartrepoRepoChartsNameVersionLabelsPost
     * @param repo The project name (required)
     * @param name The chart name (required)
     * @param version The chart version (required)
     * @param label The label being marked to the chart version (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call chartrepoRepoChartsNameVersionLabelsPostCall(String repo, String name, String version, Label label, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = label;

        // create path and map variables
        String localVarPath = "/chartrepo/{repo}/charts/{name}/{version}/labels"
            .replaceAll("\\{" + "repo" + "\\}", apiClient.escapeString(repo.toString()))
            .replaceAll("\\{" + "name" + "\\}", apiClient.escapeString(name.toString()))
            .replaceAll("\\{" + "version" + "\\}", apiClient.escapeString(version.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json", "text/plain"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "basicAuth" };
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call chartrepoRepoChartsNameVersionLabelsPostValidateBeforeCall(String repo, String name, String version, Label label, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'repo' is set
        if (repo == null) {
            throw new ApiException("Missing the required parameter 'repo' when calling chartrepoRepoChartsNameVersionLabelsPost(Async)");
        }
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new ApiException("Missing the required parameter 'name' when calling chartrepoRepoChartsNameVersionLabelsPost(Async)");
        }
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new ApiException("Missing the required parameter 'version' when calling chartrepoRepoChartsNameVersionLabelsPost(Async)");
        }
        
        // verify the required parameter 'label' is set
        if (label == null) {
            throw new ApiException("Missing the required parameter 'label' when calling chartrepoRepoChartsNameVersionLabelsPost(Async)");
        }
        

        com.squareup.okhttp.Call call = chartrepoRepoChartsNameVersionLabelsPostCall(repo, name, version, label, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Mark label to chart.
     * Mark label to the specified chart version.
     * @param repo The project name (required)
     * @param name The chart name (required)
     * @param version The chart version (required)
     * @param label The label being marked to the chart version (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void chartrepoRepoChartsNameVersionLabelsPost(String repo, String name, String version, Label label) throws ApiException {
        chartrepoRepoChartsNameVersionLabelsPostWithHttpInfo(repo, name, version, label);
    }

    /**
     * Mark label to chart.
     * Mark label to the specified chart version.
     * @param repo The project name (required)
     * @param name The chart name (required)
     * @param version The chart version (required)
     * @param label The label being marked to the chart version (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> chartrepoRepoChartsNameVersionLabelsPostWithHttpInfo(String repo, String name, String version, Label label) throws ApiException {
        com.squareup.okhttp.Call call = chartrepoRepoChartsNameVersionLabelsPostValidateBeforeCall(repo, name, version, label, null, null);
        return apiClient.execute(call);
    }

    /**
     * Mark label to chart. (asynchronously)
     * Mark label to the specified chart version.
     * @param repo The project name (required)
     * @param name The chart name (required)
     * @param version The chart version (required)
     * @param label The label being marked to the chart version (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call chartrepoRepoChartsNameVersionLabelsPostAsync(String repo, String name, String version, Label label, final ApiCallback<Void> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = chartrepoRepoChartsNameVersionLabelsPostValidateBeforeCall(repo, name, version, label, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
}
