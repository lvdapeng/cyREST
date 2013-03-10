package org.cytoscape.rest.internal.translator;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.rest.Translator;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CyNetwork2JSONTranslator implements Translator<String, CyNetwork> {

	private final ObjectMapper jackson;

	public CyNetwork2JSONTranslator() {
		jackson = new ObjectMapper();

		final CyJacksonModule module = new CyJacksonModule();
		jackson.registerModule(module);
	}

	/**
	 * Convert CyNetwork Object to JSON
	 */
	public String translate(final CyNetwork network) {
		try {
			return jackson.writeValueAsString(network);
		} catch (Exception e) {
			return "";
		}
	}

}