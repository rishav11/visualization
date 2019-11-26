import pprint as pp
import json
import re

# ef find():

def main():
	f = open("../source_code/variables_log.txt", "r")
	main_string = f.read()
	main_string_array = main_string.split('\n')

	new_array = list(filter(lambda x: x != '+++++++++++++++++++++++++++++++', main_string_array))
	
	big_list = []
	small_list = []
	counter = 0
	number_of_lines = 3

	# segregates per log lines into individual arrays
	for i in new_array:
		if counter == 0:
			small_list.append(i)
			counter = 1
		elif counter % number_of_lines == number_of_lines - 1:
			small_list.append(i)
			big_list.append(small_list)
			counter += 1
			small_list = []
		else:
			small_list.append(i)
			counter += 1

	# pp.pprint(big_list)

	final_dict = {}
	for i in big_list:

		try:
			ast = re.search('Class name: ast.(.+?)\@', i[0]).group(1) # RULE
			class_name = re.search('Class name: ast.(.*)$', i[0]).group(1) # RULE@49476842
			global_variable = re.search('Variable name: (.*)$', i[1]).group(1) #
			variable_value = re.search('Variable value: (.*)$', i[2]).group(1)
		except AttributeError:
			pp.pprint('error')
		
		if ast not in final_dict:

			small_dict = {}
			small_dict[global_variable] = [variable_value]

			mid_dict = {}
			mid_dict[class_name] = small_dict

			final_dict[ast] = mid_dict
			
		elif class_name not in final_dict[ast]: # if ast in final_dict, but there's no class_name

			small_dict = {}
			small_dict[global_variable] = [variable_value]
			final_dict[ast][class_name] = small_dict

		# if ast and class_name in final dict and but global variable is not there
		elif global_variable not in final_dict[ast][class_name]:

			final_dict[ast][class_name][global_variable] = [variable_value]

		# if there's already item(s) in the global variable array, add to it
		elif len(final_dict[ast][class_name][global_variable]) != 0:

			final_dict[ast][class_name][global_variable].append(variable_value)

	# pp.pprint(final_dict)

	with open('output.js', 'w') as outfile:
			outfile.write("data=\n")
			json.dump(final_dict, outfile, indent=4)
	
	f.close()


main()