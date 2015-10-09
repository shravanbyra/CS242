import json
from Network import Network

__author__ = 'ShravanB'


class NetworkController:
    def __init__(self):
        self.network = Network()

    def run(self):
        json_file = '../data/CSAir_revised.json'
        self.create_network(json_file)
        self.print_menu()
        while True:
            i = raw_input('Enter a menu code: ')

            if i == '0':
                exit(0)
            elif i == '1':
                print self.network.get_metros()
            elif i == '2':
                code = raw_input('Enter a city code: ')
                print '\n' + self.network.get_metro_info(code) + '\n'
            elif i == '3':
                self.get_network_statistics()
            elif i == '4':
                print '\n' + self.network.get_url() + '\n'
            elif i == '5':
                self.add_info()
            elif i == '6':
                self.remove_info()
            elif i == '7':
                self.edit_metro()
            elif i == '8':
                self.route_info()
            elif i == '9':
                self.network.save_network()
                print 'Network saved.'
            elif i.lower() == 'menu':
                self.print_menu()
            else:
                print 'Input not understood.'

    '''
    print menu for TUI
    '''

    @staticmethod
    def print_menu():
        print '\nWELCOME TO CSAIR, PLEASE SELECT A NUMBER' \
              '\n0: EXIT' \
              '\n1: CITIES' \
              '\n2: CITY INFO' \
              '\n3: CSAir STATISTICS' \
              '\n4: NETWORK MAP' \
              '\n5: ADD INFO' \
              '\n6: REMOVE INFO' \
              '\n7: EDIT METRO' \
              '\n8: GET TRIP INFO' \
              '\n9: SAVE NETWORK' \
              '\n\nType menu to see menu'

    '''
    create network from file
    @param json_file: json file to parse for network
    '''

    def create_network(self, json_file):
        f = open(json_file, 'r')
        data = json.loads(f.read())
        self.network.create_metros(data['metros'])
        self.network.create_routes(data['routes'])
        f.close()

    def get_network_statistics(self):

        print '\n1: LONGEST ROUTE' \
              '\n2: SHORTEST ROUTE' \
              '\n3: AVERAGE ROUTE' \
              '\n4: LARGEST POPULATION' \
              '\n5: SMALLEST POPULATION' \
              '\n6: AVERAGE POPULATION' \
              '\n7: CONTINENTS SERVED' \
              '\n8: CITY HUBS'
        i = raw_input('Enter a code: ')

        if i == '1':
            print '\n' + self.network.get_longest_route() + '\n'
        elif i == '2':
            print '\n' + self.network.get_shortest_route() + '\n'
        elif i == '3':
            print '\n' + self.network.get_average_route() + '\n'
        elif i == '4':
            print '\n' + self.network.get_biggest_city() + '\n'
        elif i == '5':
            print '\n' + self.network.get_smallest_city() + '\n'
        elif i == '6':
            print '\n' + self.network.get_average_size() + '\n'
        elif i == '7':
            print '\n' + self.network.get_continents() + '\n'
        elif i == '8':
            print '\n' + self.network.get_city_hubs() + '\n'

    def add_info(self):
        print '\n1: ADD METRO' \
              '\n2: ADD ROUTE'
        i = raw_input('Enter a code: ')

        if i == '1':
            metro_code = raw_input('Code: ')
            metro_name = raw_input('Name: ')
            metro_country = raw_input('Country: ')
            metro_timezone = raw_input('Timezone: ')
            metro_coordinates = raw_input('Coordinates: ')
            metro_population = raw_input('Population: ')
            metro_region = raw_input('Region: ')
            metro_continent = raw_input('Continent: ')

            if metro_population < 0:
                print "Invalid population. Try again."
                return

            data = {
                'code': metro_code,
                'name': metro_name,
                'country': metro_country,
                'continent': metro_continent,
                'timezone': metro_timezone,
                'coordinates': metro_coordinates,
                'population': metro_population,
                'region': metro_region
            }
            self.network.add_metro(data)
            print "Added metro"
        elif i == '2':
            metro_origin = raw_input('Origin code:')
            metro_destination = raw_input('Destination code: ')
            route_distance = raw_input('Distance: ')

            if route_distance < 0:
                print "invalid distance. Try Again"
                return
            data = {'routes': []}
            both = raw_input('Would you like to remove both routes? (Y/N)')
            if both.lower() == 'y':
                data['routes'].append({
                    'ports': [metro_destination, metro_origin],
                    'distance': route_distance
                })
            data['routes'].append({
                'ports': [metro_origin, metro_destination],
                'distance': route_distance
            })

    def remove_info(self):
        print '\n1: REMOVE METRO' \
              '\n2: REMOVE ROUTE'
        i = raw_input('Enter a menu code: ')

        if i == '1':
            metro_remove = raw_input('Enter a metro code: ')
            self.network.remove_metro(metro_remove)
            print "Metro Removed"
        elif i == '2':
            origin = raw_input('Enter origin metro code: ')
            dest = raw_input('Enter destination metro code: ')
            both = raw_input('Would you like to remove both routes? (Y/N)')
            if both.lower() == 'y':
                self.network.remove_route(dest, origin)
            self.network.remove_route(origin, dest)
            print "Route removed"

    def edit_metro(self):
        metro_code = raw_input('Code: ')
        print "Enter -1 if you wish to leave specific part of metro the same"
        metro_name = raw_input('Name: ')
        metro_country = raw_input('Country: ')
        metro_timezone = raw_input('Timezone: ')
        metro_coordinates = raw_input('Coordinates: ')
        metro_population = raw_input('Population: ')
        metro_region = raw_input('Region: ')
        metro_continent = raw_input('Continent: ')

        data = {
            'code': metro_code,
            'name': metro_name,
            'country': metro_country,
            'continent': metro_continent,
            'timezone': metro_timezone,
            'coordinates': metro_coordinates,
            'population': metro_population,
            'region': metro_region
        }
        self.network.edit_metro(data)
        print "\nMetro has been updated\n"
        print self.network.get_metro_info(metro_code)

    def route_info(self):
        print "Enter -1 to end trip list"
        i = 0
        metro_list = []
        while True:
            i = raw_input('Enter a metro code or -1 to end: ')
            if i == '-1':
                print self.network.get_trip_info(metro_list)
                return
            else:
                metro_list.append(i)


def main():
    nc = NetworkController()
    nc.run()


if __name__ == '__main__':
    main()
