/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package internationalization.translate.client;

import internationalization.translate.client.db.UiTextKey;
import internationalization.translate.client.db.UiTextKeyTable;
import internationalization.translate.client.db.UiTextTranslationTable;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("database")
public interface DatabaseService extends RemoteService {
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static DatabaseServiceAsync instance;
		public static DatabaseServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(DatabaseService.class);
			}
			return instance;
		}
	}
	
	void clearKeys();
	
	UiTextKeyTable getUiTextKeys(); 
	UiTextTranslationTable getUiTextTranslationTable(String lang);
}
