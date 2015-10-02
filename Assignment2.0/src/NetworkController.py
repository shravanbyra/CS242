import json
from Network import Network

__author__ = 'ShravanB'


class NetworkController:
    def __init__(self):
        self.network = Network()

    def run(self):
        json_file = 'C:\Users\ShravanB\Documents\College\CS242\CSAir\data\CSAir.json'
        self.create_network(json_file)
        self.print_menu()
        while True:
            i = raw_input('Enter a menu code: ')

            if i == '0':
                exit(0)
            elif i == '1':
                print self.network.get_metros()
            elif i == '2':
                print 'Enter a city code: '
                code = raw_input()
                print self.network.get_metro_info(code) + '\n'
            elif i == '3':
                print self.network.get_longest_route() + '\n'
            elif i == '4':
                print self.network.get_shortest_route() + '\n'
            elif i == '5':
                print self.network.get_average_route() + '\n'
            elif i == '6':
                print self.network.get_biggest_city() + '\n'
            elif i == '7':
                print self.network.get_smallest_city() + '\n'
            elif i == '8':
                print self.network.get_average_size() + '\n'
            elif i == '9':
                print self.network.get_continents() + '\n'
            elif i == '10':
                print self.network.get_city_hubs() + '\n'
            elif i == '11':
                print self.network.get_url() + '\n'
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
              '\n3: LONGEST ROUTE' \
              '\n4: SHORTEST ROUTE' \
              '\n5: AVERAGE ROUTE' \
              '\n6: LARGEST POPULATION' \
              '\n7: SMALLEST POPULATION' \
              '\n8: AVERAGE POPULATION' \
              '\n9: CONTINENTS SERVED' \
              '\n10: CITY HUBS' \
              '\n11: NETWORK MAP' \
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


def main():
    nc = NetworkController()
    nc.run()


if __name__ == '__main__':
    main()