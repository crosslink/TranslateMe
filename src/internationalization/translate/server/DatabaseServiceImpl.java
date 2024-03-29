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
package internationalization.translate.server;

import internationalization.translate.client.DatabaseService;
import internationalization.translate.client.db.UiTextKey;
import internationalization.translate.client.db.UiTextKeyTable;
import internationalization.translate.client.db.UiTextTranslationTable;
import internationalization.translate.server.db.UiTextKeyTableImpl;
import internationalization.translate.server.db.UiTextTranslationTableImpl;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DatabaseServiceImpl extends RemoteServiceServlet implements DatabaseService {

	@Override
	public void clearKeys() {
		UiTextKeyTableImpl.deleteKeys();
	}

	@Override
	public UiTextKeyTable getUiTextKeys() {
		return UiTextKeyTableImpl.getKeys();
	}

	@Override
	public UiTextTranslationTable getUiTextTranslationTable(String langKey) {
		return UiTextTranslationTableImpl.load(langKey);
	}
}
