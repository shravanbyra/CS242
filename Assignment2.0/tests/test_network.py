import json
import unittest
from src.Network import Network

__author__ = 'ShravanB'


class TestNetwork(unittest.TestCase):
    def test_shortest_route(self):
        n = Network()
        json_file = 'C:\Users\ShravanB\Documents\College\CS242\CSAir\data\CSAir.json'
        f = open(json_file, 'r')
        data = json.loads(f.read())
        n.create_metros(data['metros'])
        n.create_routes(data['routes'])
        shortest_route = "Shortest Route: WAS - NYC \nDistance: 334"
        self.assertEqual(n.get_shortest_route(), shortest_route)

    def test_longest_route(self):
        n = Network()
        json_file = 'C:\Users\ShravanB\Documents\College\CS242\CSAir\data\CSAir.json'
        f = open(json_file, 'r')
        data = json.loads(f.read())
        n.create_metros(data['metros'])
        n.create_routes(data['routes'])
        longest_route = "Longest Route: SYD - LAX \nDistance: 12051"
        self.assertEqual(n.get_longest_route(), longest_route)

    def test_avg_route(self):
        n = Network()
        json_file = 'C:\Users\ShravanB\Documents\College\CS242\CSAir\data\CSAir.json'
        f = open(json_file, 'r')
        data = json.loads(f.read())
        n.create_metros(data['metros'])
        n.create_routes(data['routes'])
        avg_route = "Average Route Distance: 2300"
        self.assertEqual(n.get_average_route(), avg_route)

    def test_biggest_city(self):
        n = Network()
        json_file = 'C:\Users\ShravanB\Documents\College\CS242\CSAir\data\CSAir.json'
        f = open(json_file, 'r')
        data = json.loads(f.read())
        n.create_metros(data['metros'])
        n.create_routes(data['routes'])
        big_city = "Largest Population: Tokyo - 34000000 people"
        self.assertEqual(n.get_biggest_city(), big_city)

    def test_biggest_city(self):
        n = Network()
        json_file = 'C:\Users\ShravanB\Documents\College\CS242\CSAir\data\CSAir.json'
        f = open(json_file, 'r')
        data = json.loads(f.read())
        n.create_metros(data['metros'])
        n.create_routes(data['routes'])
        small_city = "Smallest Population: Essen - 589900 people"
        self.assertEqual(n.get_smallest_city(), small_city)

    def test_avg_city(self):
        n = Network()
        json_file = 'C:\Users\ShravanB\Documents\College\CS242\CSAir\data\CSAir.json'
        f = open(json_file, 'r')
        data = json.loads(f.read())
        n.create_metros(data['metros'])
        n.create_routes(data['routes'])
        avg_city = "Average City Population: 11796143"
        self.assertEqual(n.get_average_size(), avg_city)

    def test_city_hub(self):
        n = Network()
        json_file = 'C:\Users\ShravanB\Documents\College\CS242\CSAir\data\CSAir.json'
        f = open(json_file, 'r')
        data = json.loads(f.read())
        n.create_metros(data['metros'])
        n.create_routes(data['routes'])
        metro_hub = "Number of Routes : 6\n" \
                    "Istanbul\n" \
                    "Hong Kong"
        self.assertEqual(n.get_city_hubs(), metro_hub)

    def test_url(self):
        n = Network()
        json_file = 'C:\Users\ShravanB\Documents\College\CS242\CSAir\data\CSAir.json'
        f = open(json_file, 'r')
        data = json.loads(f.read())
        n.create_metros(data['metros'])
        n.create_routes(data['routes'])
        url = "http://www.gcmap.com/mapui?P=SCL-LIM,+LIM-MEX,+LIM-BOG," \
              "+MEX-LAX,+MEX-CHI,+MEX-MIA,+MEX-BOG,+BOG-MIA,+BOG-SAO,+BOG-BUE," \
              "+BUE-SAO,+SAO-MAD,+SAO-LOS,+LOS-KRT,+LOS-FIH,+FIH-KRT,+FIH-JNB," \
              "+JNB-KRT,+KRT-CAI,+CAI-ALG,+CAI-IST,+CAI-BGW,+CAI-RUH,+ALG-MAD," \
              "+ALG-PAR,+ALG-IST,+MAD-NYC,+MAD-LON,+MAD-PAR,+LON-NYC,+LON-ESS," \
              "+LON-PAR,+PAR-ESS,+PAR-MIL,+MIL-ESS,+MIL-IST,+ESS-LED,+LED-MOW," \
              "+LED-IST,+MOW-THR,+MOW-IST,+IST-BGW,+BGW-THR,+BGW-KHI,+BGW-RUH," \
              "+THR-DEL,+THR-KHI,+THR-RUH,+RUH-KHI,+KHI-DEL,+KHI-BOM,+DEL-CCU," \
              "+DEL-MAA,+DEL-BOM,+BOM-MAA,+MAA-CCU,+MAA-BKK,+MAA-JKT,+CCU-HKG," \
              "+CCU-BKK,+BKK-HKG,+BKK-SGN,+BKK-JKT,+HKG-SHA,+HKG-TPE,+HKG-MNL," \
              "+HKG-SGN,+SHA-PEK,+SHA-ICN,+SHA-TYO,+SHA-TPE,+PEK-ICN,+ICN-TYO," \
              "+TYO-SFO,+TYO-OSA,+OSA-TPE,+TPE-MNL,+MNL-SFO,+MNL-SYD,+MNL-SGN," \
              "+SGN-JKT,+JKT-SYD,+SYD-LAX,+LAX-SFO,+LAX-CHI,+SFO-CHI,+CHI-YYZ," \
              "+CHI-ATL,+ATL-WAS,+ATL-MIA,+MIA-WAS,+WAS-YYZ,+WAS-NYC,+NYC-YYZ,+"
        self.assertEqual(n.get_url(), url)
