import unittest
from src.Metro import Metro

__author__ = 'ShravanB'


class TestMetro(unittest.TestCase):

    def test_metro(self):
        metro_dict = {"code": "SCL",
                        "name": "Santiago",
                        "country": "CL",
                        "continent": "South America",
                        "timezone": -4,
                        "coordinates": {"S": 33, "W": 71},
                        "population": 6000000,
                        "region": 1
                        }
        m = Metro(metro_dict)
        m.add_route('MEX', 99999)
        test_info = 'Name: Santiago' \
               + '\nCode: SCL' \
               + '\nCountry: CL' \
               + '\nContinent: South America' \
               + '\nTime Zone: -4' \
               + '\nCoordinates: {\'S\': 33, \'W\': 71}' \
               + '\nPopulation: 6000000'\
               + '\nRegion: 1' \
               + '\nRoutes:  ' + m.get_routes() + '\n'

        self.assertEqual(m.get_info(), test_info)


