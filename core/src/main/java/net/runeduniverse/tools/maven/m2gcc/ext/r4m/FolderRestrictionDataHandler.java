/*
 * Copyright © 2025 VenaNocta (venanocta@gmail.com)
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
 */
package net.runeduniverse.tools.maven.m2gcc.ext.r4m;

import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.configuration.PlexusConfiguration;

import net.runeduniverse.tools.maven.r4m.pem.converter.ADataHandler;
import net.runeduniverse.tools.maven.r4m.pem.converter.api.ConfigurationFactory;
import net.runeduniverse.tools.maven.r4m.pem.converter.api.DataHandler;
import net.runeduniverse.tools.maven.r4m.pem.model.DataEntry;

@Component(role = DataHandler.class, hint = FolderRestriction.CANONICAL_NAME)
public class FolderRestrictionDataHandler extends ADataHandler {

	@Override
	protected PlexusConfiguration toConfig(final ConfigurationFactory<PlexusConfiguration> factory,
			final DataEntry entry) {
		if (!(entry instanceof FolderRestriction))
			return null;

		final FolderRestriction data = (FolderRestriction) entry;
		final PlexusConfiguration cnf = factory.create(FolderRestriction.HINT);

		setValueAsId(cnf, data.getValue());
		setOptionalAttributeSkipDefault(cnf, "exists", data.getExists(), null);
		setOptionalAttributeSkipDefault(cnf, "readable", data.getReadable(), null);

		return cnf;
	}
}
