import requests
import logging


def log_request_result(request, desired_code, success_message):
    if request.status_code != desired_code:
        logging.error(request.text)
    else:
        logging.info(success_message)


elastic = 'http://localhost:9200'
index_name = 'hr_system'
mapping_file_name = 'mapping/hr_system_mapping.json'
data_file_name = 'data/users.index'

logging.basicConfig(level=logging.INFO)
mapping_file = open(mapping_file_name, 'r')
mapping_json = mapping_file.read()
mapping_file.close()
mapping_url = elastic + '/' + index_name
mapping_delete_request = requests.delete(mapping_url)
log_request_result(mapping_delete_request, 200, 'Mapping for index {0} was deleted.'.format(index_name))
mapping_put_request = requests.put(mapping_url, mapping_json)
log_request_result(mapping_put_request, 200, 'Mapping for index {0} was created.'.format(index_name))
index_file = open(data_file_name, 'r')
index = index_file.read()
index_file.close()
index_request = requests.post(elastic + '/_bulk', index, headers={'Content-Type': 'application/x-ndjson'})
logging.info(index_request.json())
