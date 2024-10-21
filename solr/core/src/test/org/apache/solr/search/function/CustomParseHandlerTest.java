package org.apache.solr.search.function;

import org.apache.solr.SolrTestCaseJ4;
import org.apache.solr.handler.component.ResponseBuilder;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.response.SolrQueryResponse;
import org.apache.solr.search.SyntaxError;
import org.apache.solr.search.facet.FacetField;
import org.apache.solr.search.facet.FacetParser;
import org.apache.solr.search.facet.FacetRequest;
import org.junit.BeforeClass;

import java.util.Collections;
import java.util.Map;


public class CustomParseHandlerTest extends SolrTestCaseJ4 {

    public static final FacetField FACET_FIELD_STUB = new FacetField();

    static class CustomParseHandler implements FacetParser.ParseHandler {
        @Override
        public Object doParse(FacetParser<?> parent, String key, Object args) {
            assertEquals("arg", args);
            return FACET_FIELD_STUB;
        }
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        initCore("solrconfig.xml", "schema.xml");
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        FacetParser.registerParseHandler("custom", new CustomParseHandler());
    }

    public void testCustomParseHandler() {
        SolrQueryRequest req = req();
        ResponseBuilder rsp = new ResponseBuilder(req, new SolrQueryResponse(), Collections.emptyList());

        FacetRequest facetRequest = FacetRequest.parse(rsp.req, Map.of("bogus",Map.of("custom","arg")));
        assertEquals(FACET_FIELD_STUB, facetRequest.getSubFacets().get("bogus"));
    }
}
