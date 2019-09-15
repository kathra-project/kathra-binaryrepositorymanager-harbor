/* 
 * Copyright 2019 The Kathra Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *
 *    IRT SystemX (https://www.kathra.org/)    
 *
 */

package org.kathra.binaryrepositorymanager;
import org.kathra.utils.ConfigManager;

/**
* @author Pierre d'Aviau de Ternay <pat@kathra.org>
*/
public class Config extends ConfigManager {
  
  private String harborUrl;
  private String harborUsername;
  private String harborPassword;
  
  public Config() {
    harborUrl = getProperty("HARBOR_URL");
    harborUsername = getProperty("HARBOR_USERNAME");
    harborPassword = getProperty("HARBOR_PASSWORD");
  }
  
  public String getHarborUrl() {
    return this.harborUrl;
  }
  
  public String getHarborUsername() {
    return this.harborUsername;
  }
  
  public String getHarborPassword() {
    return this.harborPassword;
  }
}
