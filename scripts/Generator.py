import json
import random
import datetime
import os


def get_random_file_line(file_name):
    file = open(file_name, 'r')
    lines = file.read().splitlines()
    return random.choice(lines)


def get_university_start_year(age):
    now = datetime.datetime.now()
    return now.year - (age - 17)


def get_education_info(start_year, duration, degree):
    university = get_random_file_line(university_file)
    education = {}
    education['university'] = university
    education_end_year = start_year + duration
    education['end_year'] = education_end_year
    education['degree'] = degree
    return education


def get_user_education_info(university_start_year):
    education = []
    degree = 1 if (user['age'] < 23) else  random.randint(1, 3)
    if (degree == 1):
        education.append(get_education_info(university_start_year, random.randint(4, 6), "Bachelor"))
    elif (degree == 2):
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


def get_user_work_experience(university_start_year):
    work_experiences = []
    work_start_year = university_start_year + random.randint(4, 8)
    current_year = datetime.datetime.now().year
    counter = work_start_year
    while counter <= current_year:
        work_experience = {}
        work_experience['start_year'] = counter
        counter = counter + random.randint(1, 15)
        work_experience['current_job'] = 'true' if (counter > current_year) else 'false'
        work_experience['end_year'] = counter if (counter <= current_year) else ''
        work_experience['company'] = get_random_file_line(companies_file)
        work_experiences.append(work_experience)
    return work_experiences


users = []
first_names_file = 'dictionaries/first_names.csv'
last_names_file = 'dictionaries/last_names.csv'
university_file = 'dictionaries/universities.csv'
companies_file = 'dictionaries/companies.csv'
for counter in range(1, 10):
    user = {}
    user['first_name'] = get_random_file_line(first_names_file)
    user['last_name'] = get_random_file_line(last_names_file)
    age = random.randint(22, 55)
    user['age'] = age
    university_start_year = get_university_start_year(age)
    user['education'] = get_user_education_info(university_start_year)
    user['work_experience'] = get_user_work_experience(university_start_year)
    users.append(user)

jsonData = json.dumps(users)

data_path = os.curdir + '\data'
if not os.path.exists(data_path):
    os.makedirs(data_path)
output = open('data/users.json', 'w')
output.write(jsonData)
