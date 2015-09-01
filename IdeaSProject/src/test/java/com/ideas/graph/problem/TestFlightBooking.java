package com.ideas.graph.problem;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class TestFlightBooking {
	private List<Airport> airports;
	private List<Route> routes;
	
	/* Use Google Guava BiMap so that we can query on both key and value. */
	private BiMap<Character, Integer> airportIdMap = HashBiMap.create();
	
	private Map<Character, Integer> populateAirportIdMapping() {
		airportIdMap.put('a', 0);
		airportIdMap.put('b', 1);
		airportIdMap.put('c', 2);
		airportIdMap.put('d', 3);
		airportIdMap.put('e', 4);
		airportIdMap.put('f', 5);
		airportIdMap.put('g', 6);
		airportIdMap.put('h', 7);
		
		return airportIdMap;
	}

	@Test
	public void testExcute() {
		airports = new ArrayList<Airport>();
		routes = new ArrayList<Route>();
		populateAirportIdMapping();
		
		for (int i = 0; i < 9; i++) {
			Airport location = new Airport("Airport - " + airportIdMap.inverse().get(i));
			airports.add(location);
		}

		/* Add some routes with same distance/weight. */
		addRoute("Route_1", airportIdMap.get('a'), airportIdMap.get('f'), 1);
		addRoute("Route_2", airportIdMap.get('b'), airportIdMap.get('a'), 1);
		addRoute("Route_3", airportIdMap.get('b'), airportIdMap.get('c'), 1);
		addRoute("Route_4", airportIdMap.get('c'), airportIdMap.get('d'), 1);
		addRoute("Route_5", airportIdMap.get('c'), airportIdMap.get('e'), 1);
		addRoute("Route_6", airportIdMap.get('d'), airportIdMap.get('e'), 1);
		addRoute("Route_7", airportIdMap.get('e'), airportIdMap.get('b'), 1);
		addRoute("Route_8", airportIdMap.get('h'), airportIdMap.get('b'), 1);

		// Test Node for given source to destination.
		AirFlightSystem airFlightSystem = new AirFlightSystem(airports, routes);
		FlightBookingImpl flightBooking = new FlightBookingImpl(airFlightSystem);
		/* This will traverse the possible paths. */
		flightBooking.execute(airports.get(airportIdMap.get('h')));
		LinkedList<Airport> path = flightBooking.getShortestPath(airports.get(airportIdMap.get('e')));

		assertNotNull(path);
		assertTrue(path.size() > 0);

		for (Airport airport : path) {
			System.out.println(airport);
		}

	}

	private void addRoute(String routeId, int sourceLocNo, int destLocNo, int distance) {
		Route route = new Route(routeId, airports.get(sourceLocNo), airports.get(destLocNo), distance);
		routes.add(route);
	}
}
