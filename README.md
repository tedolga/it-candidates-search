### Index ###

```
PUT localhost:9200/candidates
{
  "mappings": {
    "external": {
      "properties": {
        "dc_attributes": {
          "type": "nested",
          "properties":{
          	"data_center":{
          		"type":"nested",
          		"properties":{
          			"name":{"type":"string"},
          			"state":{"type":"string"}
          		}
          	}
          }
        }
      }
    }
  }
}
```

### NESTED query to search ###

```
{
    "query": {
        "nested" : {
            "path" : "dc_attributes.data_center",
            "score_mode" : "avg",
            "query" : {
                "bool" : {
                    "must" : [
                    { "match" : {"dc_attributes.data_center.name" : "DC1"} },
                    { "match" : {"dc_attributes.data_center.state" : "CA"} }
                    ]
                }
            }
        }
    }
}
```
