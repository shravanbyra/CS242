from Metro import Metro
__author__ = 'ShravanB'

'''
Network is the "graph" class
calculates all statistic calculations
'''
class Network:

    def __init__(self):
        self.metros = {}
        self.route_list = {}

    '''
    creates dictionary of metros
    key: metro code
    value: metro
    @param metro_list: json/dictionary formatted metros
    '''
    def create_metros(self, metro_list):
        for m in metro_list:
            self.metros[m['code']] = (Metro(m))

    '''
    Add route to routes
    @param destination: destination code
    @param distance: distance to destination
    '''
    def create_routes(self, route_list):
        self.route_list = route_list
        for r in route_list:
            self.metros[r['ports'][0]].add_route(r['ports'][1], r['distance'])
            self.metros[r['ports'][1]].add_route(r['ports'][0], r['distance'])

    '''
    get metros served in network
    '''
    def get_metros(self):
        metro_string = ""
        for m in self.metros:
            metro_string += '{0} - {1} \n'.format(self.metros[m].name, self.metros[m].code)
        return metro_string

    '''
    get info of a specific metro
    @param metro_code: 3 letter code of a metro
    '''
    def get_metro_info(self, metro_code):
        if metro_code in self.metros.keys():
            return self.metros[metro_code].get_info()
        return "metro not found"

    '''
    get shortest route in the network
    uses lambda function to calculate min
    '''
    def get_shortest_route(self):
        shortest_route = min(self.route_list, key=lambda r: r['distance'])
        return 'Shortest Route: {0} - {1} \nDistance: {2}'.format(shortest_route['ports'][0], shortest_route['ports'][1], shortest_route['distance'])

    '''
    get longest route in the network
    uses lambda function to calculate max
    '''
    def get_longest_route(self):
        longest_route = max(self.route_list, key=lambda r: r['distance'])
        return 'Longest Route: {0} - {1} \nDistance: {2}'.format(longest_route['ports'][0], longest_route['ports'][1], longest_route['distance'])

    '''
    get average length of a route in the network
    '''
    def get_average_route(self):
        route_sum = 0
        num_routes = 0
        for r in self.route_list:
            route_sum += r['distance']
            num_routes += 1
        return 'Average Route Distance: ' + str(route_sum/num_routes)

    '''
    get the largest city in the network
    '''
    def get_biggest_city(self):
        biggest_city = ""
        biggest_pop = 0
        for m in self.metros:
            if self.metros[m].population > biggest_pop:
                biggest_city = self.metros[m].name
                biggest_pop = self.metros[m].population
        return 'Largest Population: {0} - {1} people'.format(biggest_city, biggest_pop)

    '''
    get the smallest city in the network
    '''
    def get_smallest_city(self):
        smallest_city = ""
        smallest_pop = 99999999
        for m in self.metros:
            if self.metros[m].population < smallest_pop:
                smallest_city = self.metros[m].name
                smallest_pop = self.metros[m].population
        return 'Smallest Population: {0} - {1} people'.format(smallest_city, smallest_pop)

    '''
    get the average size of a city in the network
    '''
    def get_average_size(self):
        num_cities = 0
        sum_pop = 0
        for m in self.metros:
            num_cities += 1
            sum_pop += self.metros[m].population
        return 'Average City Population: ' + str(sum_pop/num_cities)

    '''
    lists all continents served by network
    lists all cities served in that continent
    '''
    def get_continents(self):
        africa = []
        n_america = []
        s_america = []
        europe = []
        asia = []
        australia = []
        antarctica = []
        for m in self.metros:
            if self.metros[m].continent.lower() == 'africa':
                africa.append(self.metros[m].name)
            if self.metros[m].continent.lower() == 'north america':
                n_america.append(self.metros[m].name)
            if self.metros[m].continent.lower() == 'south america':
                s_america.append(self.metros[m].name)
            if self.metros[m].continent.lower() == 'europe':
                europe.append(self.metros[m].name)
            if self.metros[m].continent.lower() == 'asia':
                asia.append(self.metros[m].name)
            if self.metros[m].continent.lower() == 'australia':
                australia.append(self.metros[m].name)
            if self.metros[m].continent.lower() == 'antarctica':
                antarctica.append(self.metros[m].name)

        continents_served = "Continents Served \n---------------------"
        if len(africa) > 0:
            continents_served += '\n---------------------\nAfrica:'
            for m in africa:
                continents_served += '\n' + m
        if len(n_america) > 0:
            continents_served += '\n---------------------\nNorth America:'
            for m in n_america:
                continents_served += '\n' + m
        if len(s_america) > 0:
            continents_served += '\n---------------------\nSouth America:'
            for m in s_america:
                continents_served += '\n' + m
        if len(europe) > 0:
            continents_served += '\n---------------------\nEurope:'
            for m in europe:
                continents_served += '\n' + m
        if len(asia) > 0:
            continents_served += '\n---------------------\nAsia:'
            for m in asia:
                continents_served += '\n' + m
        if len(australia) > 0:
            continents_served += '\n---------------------\nAustralia:'
            for m in australia:
                continents_served += '\n' + m
        if len(antarctica) > 0:
            continents_served += '\n---------------------\nAntarctica:'
            for m in antarctica:
                continents_served += '\n' + m

        return continents_served

    '''
    gets the cities with the most routes
    '''
    def get_city_hubs(self):
        num_routes = 0
        metro_names = []
        for m in self.metros:
            if len(self.metros[m].routes) > num_routes:
                metro_names = [self.metros[m].name]
                num_routes = len(self.metros[m].routes)
            elif len(self.metros[m].routes) == num_routes:
                metro_names.append(self.metros[m].name)

        city_hubs = "Number of Routes : " + str(num_routes)
        for m in metro_names:
            city_hubs += '\n' + m

        return city_hubs

    '''
    get the url associated with
    CSAir network from gcmap
    '''
    def get_url(self):
        url = "http://www.gcmap.com/mapui?P="
        for r in self.route_list:
            url += r['ports'][0] + "-" + r['ports'][1] + ',+'
        return url



