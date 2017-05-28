import json
import random
import datetime
import os
import csv


def get_random_file_line(file_name):
    file = open(file_name, 'r')
    lines = file.read().splitlines()
    file.close()
    return random.choice(lines)


def get_university_start_year(age):
    now = datetime.datetime.now()
    return now.year - (age - 17)


def get_education_info(start_year, duration, degree):
    university = get_random_file_line(university_file)
    education = {'university': university}
    education_end_year = start_year + duration
    education['end_year'] = education_end_year
    education['degree'] = degree
    return education


def get_user_education_info(university_start_year):
    education = []
    degree = 1 if (user['age'] < 23) else  random.randint(1, 3)
    if degree == 1:
        education.append(get_education_info(university_start_year, random.randint(4, 6), "Bachelor"))
    elif degree == 2:
        bacc_info = get_education_info(university_start_year, random.randint(4, 6), "Bachelor")
        master_info = get_education_info(bacc_info['end_year'] + random.randint(0, 3), random.randint(2, 4), "Master")
        education.append(bacc_info)
        education.append(master_info)
    else:
        bacc_info = get_education_info(university_start_year, random.randint(4, 6), "Bachelor")
        master_info = get_education_info(bacc_info['end_year'] + random.randint(0, 3), random.randint(2, 4), "Master")
        phd_info = get_education_info(master_info['end_year'] + random.randint(1, 3), random.randint(2, 8), "PhD")
        education.append(bacc_info)
        education.append(master_info)
        education.append(phd_info)
    return education


def get_technologies_list(file_name, specialization, max_count, mandatory):
    mid_result = []
    if not mandatory:
        add_skill = random.choice([False, True])
        if not add_skill:
            return mid_result

    file = open(file_name, 'r')
    reader = csv.DictReader(file)
    for row in reader:
        if row[specialization] == '1':
            mid_result.append(random.choice(row['technologies'].split(sep=':')))
    if len(mid_result) == 0:
        return mid_result
    tech_count = random.randint(1, max_count if max_count <= len(mid_result) else len(mid_result))
    result = []
    while len(result) < tech_count:
        tech = random.choice(mid_result)
        result.append(tech)
        mid_result.remove(tech)
    return result


def add_positions_info(work_experiences):
    it_specialization = random.choices(it_specializations, weights=[7, 5, 2, 2])[0]
    spec_positions = get_random_file_line('dictionaries/' + it_specialization + '_positions.txt').split(':')[0].split(
        ',')
    adv_spec_positions = [] if (it_specialization == 'ba' or it_specialization == 'cm')else get_random_file_line(
        'dictionaries/' + it_specialization + '_adv_positions.txt').split(':')[0].split(',')
    common_adv_positions = [] if (it_specialization == 'cm') else get_random_file_line(
        'dictionaries/common_adv_positions.txt').split(':')[0].split(',')
    positions = []
    positions.extend(spec_positions)
    positions.extend(adv_spec_positions)
    positions.extend(common_adv_positions)
    while len(work_experiences) > len(positions):
        positions_copy = positions
        positions = []
        for pos_counter in range(0, len(positions_copy)):
            rand_count = random.randint(2, 4)
            for copy_counter in range(0, rand_count):
                positions.append(positions_copy[pos_counter])
    for exp_counter in range(0, len(work_experiences)):
        work_experiences[exp_counter]['position'] = positions[exp_counter]
        work_experiences[exp_counter]['technologies'] = get_project_technologies(positions[exp_counter])


def get_project_technologies(position):
    result = []
    spec = position_spec_map[position]
    sub_spec = spec['main']
    if 'dev' == spec['main']:
        sub_spec = spec['sub'] if spec['sub'] != 'any' else random.choice(['java', 'c', 'c#', '.net'])
    spec_files_path = '{0}/dictionaries/{1}_skills'.format(os.curdir, spec['main'])
    spec_files = os.listdir(spec_files_path)
    for file_name in spec_files:
        skill_info = skills_map[file_name.replace('.csv', '')]
        result.extend(
            get_technologies_list(spec_files_path + '/' + file_name, sub_spec, skill_info['max_count'],
                                  skill_info['mandatory']))
    return result


def get_user_work_experience(university_start_year):
    work_experiences = []
    work_start_year = university_start_year + random.randint(4, 8)
    current_year = datetime.datetime.now().year
    year_counter = work_start_year
    while year_counter <= current_year:
        work_experience = {}
        work_experience['start_year'] = year_counter
        year_counter = year_counter + random.randint(1, 15)
        work_experience['current_job'] = 'true' if (year_counter > current_year) else 'false'
        work_experience['end_year'] = year_counter if (year_counter <= current_year) else None
        work_experience['company'] = get_random_file_line(companies_file)
        work_experiences.append(work_experience)
    add_positions_info(work_experiences)
    return work_experiences


def get_position_spec_map():
    result_map = {}
    for prefix in ['ba', 'cm', 'common_adv', 'dev_adv', 'dev', 'test_adv', 'test']:
        file = open('dictionaries/' + prefix + '_positions.txt', 'r')
        file_lines = file.read().splitlines()
        for file_line in file_lines:
            columns = file_line.split(':')
            positions = columns[0].split(',')
            spec = {'main': columns[1], 'sub': None if len(columns) < 3 else columns[2]}
            for position in positions:
                result_map[position] = spec
    return result_map


first_names_file = 'dictionaries/first_names.csv'
last_names_file = 'dictionaries/last_names.csv'
university_file = 'dictionaries/universities.csv'
companies_file = 'dictionaries/companies.csv'
it_specializations = ['dev', 'test', 'ba', 'cm']
position_spec_map = get_position_spec_map()
skills_map = {'data_bases': {'max_count': 2, 'mandatory': False},
              'frameworks': {'max_count': 3, 'mandatory': False},
              'main_language': {'max_count': 1, 'mandatory': True},
              'os': {'max_count': 3, 'mandatory': False},
              'programming_languages': {'max_count': 2, 'mandatory': False},
              'scripting_languages': {'max_count': 2, 'mandatory': False},
              'web_containers': {'max_count': 2, 'mandatory': False},
              'web_ui': {'max_count': 3, 'mandatory': False},
              'building_tools': {'max_count': 2, 'mandatory': False},
              'ba_tech': {'max_count': 4, 'mandatory': True},
              'dev_methodologies': {'max_count': 3, 'mandatory': True},
              'office': {'max_count': 3, 'mandatory': False},
              'req_docs': {'max_count': 3, 'mandatory': False},
              'cm': {'max_count': 3, 'mandatory': True},
              'no_sql': {'max_count': 2, 'mandatory': False},
              'vcs': {'max_count': 2, 'mandatory': False},
              'virtualization': {'max_count': 1, 'mandatory': False}
              }

data_lines = []
for counter in range(1, 101):
    user = {}
    user['first_name'] = get_random_file_line(first_names_file)
    user['last_name'] = get_random_file_line(last_names_file)
    age = random.randint(22, 55)
    user['age'] = age
    university_start_year = get_university_start_year(age)
    user['education'] = get_user_education_info(university_start_year)
    user['work_experience'] = get_user_work_experience(university_start_year)
    index_command = {'index': {'_index': 'hr_system', '_type': 'candidates', '_id': str(counter)}}
    data_lines.append(json.dumps(index_command) + "\n")
    data_lines.append(json.dumps(user) + "\n")

data_path = os.curdir + '\data'
if not os.path.exists(data_path):
    os.makedirs(data_path)
output = open('data/users.index', 'w')
output.writelines(data_lines)
output.close()
