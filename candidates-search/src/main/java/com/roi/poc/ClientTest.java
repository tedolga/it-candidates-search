package com.roi.poc;

public class ClientTest {
//    public static void main(String[] args) throws UnknownHostException {
//        Settings settings = Settings.builder()
//                .put("cluster.name", "my-cluster")
//                .put("node.name","node-1")
//                .put("client.transport.sniff", true)
//                .build();
//        TransportClient client = new PreBuiltTransportClient(settings);
//        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
//        List<DiscoveryNode> discoveryNodes = client.connectedNodes();
//        System.out.println("discoveryNodes = " + discoveryNodes);
//        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("Java Developer", "work_experience.position", "work_experience.technologies");
//        multiMatchQueryBuilder.type(MultiMatchQueryBuilder.Type.CROSS_FIELDS);
//        SearchResponse response = client.prepareSearch("hr_system")
//                .setTypes("candidates")
//                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                .setQuery(QueryBuilders.nestedQuery("work_experience",multiMatchQueryBuilder, ScoreMode.Avg))                 // Query
//                .get();
//        System.out.println(response.getHits().getTotalHits());
//        client.close();
//    }
}
