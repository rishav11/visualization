import pprint as pp
import json

def main():
	f = open("../source_code/variables_log.txt", "r")
	main_string = f.read()
	main_string_array = main_string.split('\n')

	new_array = list(filter(lambda x: x != '+++++++++++++++++++++++++++++++', main_string_array))

	big_list = []
	small_list = []
	counter = 0
	for i in new_array:
		if counter % 3 == 0:
			big_list.append(small_list)
			small_list = [i]
			counter += 1
			continue
		else:
			small_list.append(i)
			counter += 1

	big_list = big_list[1:]

	final_array = []
	tmp_quantifier = ""
	for i in big_list:
		if "EXPRESSION" in i[0]:
			new_dict = {}
			if "name" in i[1]:
				new_dict[i[0][16:]] = {"name": i[2][16:]}
				final_array.append(new_dict)

		elif "QUANTIFIER" in i[0]:
			tmp_quantifier = i[2][16:]

		elif "KEYWORD" in i[0]:
			if 'rules' not in final_array[-1]:
				final_array[-1]['rules'] = []
			final_array[-1]['rules'].append({tmp_quantifier: i[2][16:]})

		elif "TEST" in i[0]:
			new_dict = {}
			if "name" == i[1][-4:]:
				new_dict[i[0][16:]] = {"name": i[2][16:]}
				final_array.append(new_dict)
			elif "tests" in i[1]:
				if 'tests' not in final_array[-1]:
					final_array[-1]['tests'] = []
				final_array[-1]['tests'].append(i[2][16:])

		elif "DIGITCONSTRAINT" in i[0]:
			dict_to_modify = final_array[-1]['rules'][-1]
			key = list(dict_to_modify.keys())[0]
			print(key)
			new_string = dict_to_modify[key] + i[2][16:]
			dict_to_modify[key] = new_string

		elif "CHARCONSTRAINT" in i[0]:
			dict_to_modify = final_array[-1]['rules'][-1]
			key = list(dict_to_modify.keys())[0]
			print(key)
			new_string = dict_to_modify[key] + i[2][16:]
			dict_to_modify[key] = new_string



	# pp.pprint(final_array)

	# json_string = json.dumps(final_array, indent=4)
	# pp.pprint(json_string)

	with open('output.txt', 'w') as outfile:
		json.dump(final_array, outfile, indent=4)

	f.close()


main()