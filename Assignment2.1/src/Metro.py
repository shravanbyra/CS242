__author__ = 'ShravanB'

'''
Metro is the "node" of the graph
holds metro info and routes of each metro
'''

class Metro:
    def __init__(self, metros):
        self.code = metros['code']
        self.name = metros['name']
        self.country = metros['country']
        self.continent = metros['continent']
        self.timezone = metros['timezone']
        self.coordinates = metros['coordinates']
        self.population = metros['population']
        self.region = metros['region']
        self.routes = {}
        self.distance = 0
    '''
    Add route to routes
    @param destination: destination code
    @param distance: distance to destination
    '''
    def add_route(self, destination, distance):
        """

        :param destination:
        :param distance:
        :return:
        """
        if destination not in self.routes:
            self.routes[destination] = distance

    '''
    returns all routes from this metro
    '''
    def get_routes(self):
        """

        :return:
        """
        destinations = ''
        for d in self.routes:
            destinations += '\n' + str(d) + ' - ' + str(self.routes[d])
        return destinations

    '''
    returns all info about metro
    includes routes
    '''
    def get_info(self):
        """

        :return:
        """
        info = 'Name: ' + self.name \
               + '\nCode: ' + self.code \
               + '\nCountry: ' + self.country \
               + '\nContinent: ' + self.continent \
               + '\nTime Zone: ' + str(self.timezone) \
               + '\nCoordinates: ' + str(self.coordinates) \
               + '\nPopulation: ' + str(self.population) \
               + '\nRegion: ' + str(self.region) \
               + '\nRoutes:  ' + self.get_routes() + '\n'

        return info
