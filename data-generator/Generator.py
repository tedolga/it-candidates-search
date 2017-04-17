import json
import random
import datetime


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
    work_experience = []
    work_start_year = university_start_year + random.randint(4, 8)
    datetime.datetime.now().year
    return work_experience


first_names_file = 'dictionaries/first_names.csv'
last_names_file = 'dictionaries/last_names.csv'
university_file = 'dictionaries/universities.csv'
user = {}
user['first_name'] = get_random_file_line(first_names_file)
user['last_name'] = get_random_file_line(last_names_file)
age = random.randint(22, 55)
user['age'] = age

university_start_year = get_university_start_year(age)
user['education'] = get_user_education_info(university_start_year)

job = {}
job['company'] = 'Grid Gain'
workExperience = []
workExperience.append(job)
user['work_experience'] = workExperience

jsonData = json.dumps(user)

output = open('data/users.json', 'w')
output.write(jsonData)
