import json
import math
import sys
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
            self.metros[m['code']] = Metro(m)

    '''
    Add route to routes
    @param destination: destination code
    @param distance: distance to destination
    '''

    def create_routes(self, route_list):
        self.route_list = route_list
        for r in route_list:
            origin_code = r['ports'][0]
            destination_code = r['ports'][1]
            distance = r['distance']
            self.metros[origin_code].add_route(destination_code, distance)

    '''
    get metros served in network
    '''

    def get_metros(self):
        metro_string = ""
        for m in self.metros.values():
            metro_string += '{0} - {1} \n'.format(m.name, m.code)
        return metro_string

    '''
    get info of a specific metro
    @param metro_code: 3 letter code of a metro
    '''

    def get_metro_info(self, metro_code):
        if metro_code in self.metros:
            return self.metros[metro_code].get_info()
        return "metro not found"

    '''
    get shortest route in the network
    uses lambda function to calculate min
    '''

    def get_shortest_route(self):
        shortest_route = min(self.route_list, key=lambda r: r['distance'])
        return 'Shortest Route: {0} - {1} \nDistance: {2}'.format(shortest_route['ports'][0],
                                                                  shortest_route['ports'][1],
                                                                  shortest_route['distance'])

    '''
    get longest route in the network
    uses lambda function to calculate max
    '''

    def get_longest_route(self):
        longest_route = max(self.route_list, key=lambda r: r['distance'])
        return 'Longest Route: {0} - {1} \nDistance: {2}'.format(longest_route['ports'][0], longest_route['ports'][1],
                                                                 longest_route['distance'])

    '''
    get average length of a route in the network
    '''

    def get_average_route(self):
        route_sum = 0
        num_routes = 0
        for r in self.route_list:
            route_sum += r['distance']
            num_routes += 1
        return 'Average Route Distance: ' + str(route_sum / num_routes)

    '''
    get the largest city in the network
    '''

    def get_biggest_city(self):
        biggest_city = ""
        biggest_pop = 0
        for m in self.metros.values():
            if m.population > biggest_pop:
                biggest_city = m.name
                biggest_pop = m.population
        return 'Largest Population: {0} - {1} people'.format(biggest_city, biggest_pop)

    '''
    get the smallest city in the network
    '''

    def get_smallest_city(self):
        smallest_city = ""
        smallest_pop = 99999999
        for m in self.metros.values():
            if m.population < smallest_pop:
                smallest_city = m.name
                smallest_pop = m.population
        return 'Smallest Population: {0} - {1} people'.format(smallest_city, smallest_pop)

    '''
    get the average size of a city in the network
    '''

    def get_average_size(self):
        num_cities = 0
        sum_pop = 0
        for m in self.metros.values():
            num_cities += 1
            sum_pop += m.population
        return 'Average City Population: ' + str(sum_pop / num_cities)

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
        for m in self.metros.values():
            if m.continent.lower() == 'africa':
                africa.append(m.name)
            elif m.continent.lower() == 'north america':
                n_america.append(m.name)
            elif m.continent.lower() == 'south america':
                s_america.append(m.name)
            elif m.continent.lower() == 'europe':
                europe.append(m.name)
            elif m.continent.lower() == 'asia':
                asia.append(m.name)
            elif m.continent.lower() == 'australia':
                australia.append(m.name)
            elif m.continent.lower() == 'antarctica':
                antarctica.append(m.name)

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
        for m in self.metros.values():
            if len(m.routes) > num_routes:
                metro_names = [m.name]
                num_routes = len(m.routes)
            elif len(m.routes) == num_routes:
                metro_names.append(m.name)

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

    '''
    save CSAir network in ../data/CSAir_out.json
    '''

    def save_network(self):
        data = {'metros': [], 'routes': []}
        for m in self.metros.values():
            data['metros'].append({
                'code': m.code,
                'name': m.name,
                'country': m.country,
                'continent': m.continent,
                'timezone': m.timezone,
                'coordinates': m.coordinates,
                'population': m.population,
                'region': m.region
            })
            for r in m.routes:
                data['routes'].append({
                    'ports': [m.code, r],
                    'distance': m.routes[r]
                })
        data_string = json.dumps(data)
        data_file = open('../data/CSAir_out.json', 'w')
        data_file.write(data_string)
        data_file.close()

    '''
    helper function that returns distances between each metro
    @param metro_stops: metro codes in trip
    '''

    def get_trip_distances(self, metro_stops):
        dist = []
        count = 0
        num_metros = len(metro_stops)
        for m in metro_stops:
            metro = self.metros[m]
            if metro is None:
                return None
            connection = False
            for route in metro.routes:
                if count + 1 == num_metros:
                    connection = True
                elif metro_stops[count + 1] == route:
                    connection = True
                    dist.append(metro.routes[route])
                    break
            if not connection:
                return None
            count += 1
        return dist

    '''
    calculates cost of trip
    @param dist: distances calculated from metro_stops in get_trip_distances()
    '''

    def get_trip_cost(self, distances):
        cost = []
        rate = .35
        for d in distances:
            cost.append(d * rate)
            if not rate == 0:
                rate -= .05
        return cost

    '''
    calculates time for the whole trip
    @param metro_stops: list of metro codes in trip
    @param distances: distances of trip calculated in get_trip_distances()
    '''

    def get_trip_time(self, distances, metro_stops):
        total_time = 0.0
        final_velocity = 750.0
        acceleration_distance = 200.0
        acceleration = (final_velocity ** 2) / (2.0 * acceleration_distance)
        for i in range(len(distances)):
            if metro_stops[i] > acceleration_distance * 2.0:
                cruising_distance = distances[i] - 400.0
                cruising_time = cruising_distance / final_velocity
                acceleration_times = math.sqrt(2.0 * acceleration_distance / acceleration) * 2.0
                total_time += cruising_time + acceleration_times
            else:
                half_distance = distances[i] / 2.0
                half_time = math.sqrt(2.0 * half_distance / acceleration)
                total_time += 2.0 * half_time

            if i + 2 < len(metro_stops):
                num_outgoing_flights = len(self.metros[metro_stops[i + 1]].routes)
                total_time += 2 - num_outgoing_flights / 6

        return total_time

    '''
    calculates statistics for trip
    @param metro_stops: list of metros codes in trip
    '''

    def get_trip_info(self, metro_stops):
        distances = self.get_trip_distances(metro_stops)
        if distances is None:
            return "Trip not viable"
        total_distance = 0
        for d in distances:
            total_distance += d

        cost = self.get_trip_cost(distances)

        total_cost = 0
        for c in cost:
            total_cost += c

        total_time = self.get_trip_time(distances, metro_stops)

        return "Total distance: " + str(total_distance) + "\n" \
               "Total cost: $" + str(total_cost) + "\n" \
               "Total time " + str(total_time) + " hrs"

    '''
    adds metro to CSAir network
    @param metro_info: dictionary of information similar to json format
    '''

    def add_metro(self, metro_info):
        self.metros[metro_info['code']] = Metro(metro_info)

    '''
    edits metro in CSAir network
    @param metro_info: dictionary of information similar to json format
    '''

    def edit_metro(self, metro_info):
        metro = self.metros[metro_info['code']]

        if not metro_info['code'] == '-1':
            metro.code = metro_info['code']
        if not metro_info['name'] == '-1':
            metro.name = metro_info['name']
        if not metro_info['country'] == '-1':
            metro.country = metro_info['country']
        if not metro_info['timezone'] == '-1':
            metro.timezone = metro_info['timezone']
        if not metro_info['coordinates'] == '-1':
            metro.coordinates = metro_info['coordinates']
        if not metro_info['population'] == '-1':
            metro.population = metro_info['population']
        if not metro_info['region'] == '-1':
            metro.region = metro_info['region']
        if not metro_info['continent'] == '-1':
            metro.continent = metro_info['continent']

    '''
    removes metro from CSAir network
    @param metro_code: code of metro to be removed
    '''

    def remove_metro(self, metro_code):
        self.metros.pop(metro_code)

        for r in self.route_list:
            origin_code = r['ports'][0]
            destination_code = r['ports'][1]
            if origin_code == metro_code:
                return

    '''
    adds route to CSAir network
    @param route_info: dictionary of information similar to json format
    '''

    def add_route(self, route_info):
        self.route_list.append(route_info)

        origin_code = route_info['ports'][0]
        destination_code = route_info['ports'][1]
        distance = route_info['distance']

        self.metros[origin_code].add_route(destination_code, distance)

    '''
    removes route from CSAir network
    @param route_info: dictionary of information similar to json format
    '''

    def remove_route(self, origin_code, destination_code):
        self.metros[origin_code].routes.pop(destination_code)

    '''
    uses djikstra's to calculate shortest path from origin to destination
    @param metro_origin: origin code
    @param metro_destination: destination_code
    '''

    def shortest_path(self, metro_origin, metro_destination):
        shortest_path = {}

        for m in self.metros:
            m.distance = sys.maxint

        unvisited_metros = self.metros[:]
        self.metros[metro_origin] = 0
        shortest_path[metro_origin] = None

        while unvisited_metros:
            current = min(unvisited_metros, key=lambda x: x.distance)
            unvisited_metros.pop(current)

        return
