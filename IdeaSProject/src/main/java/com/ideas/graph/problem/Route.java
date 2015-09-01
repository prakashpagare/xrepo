package com.ideas.graph.problem;

/**
 * Route class is like edges of a graph.
 * @author
 *
 */
public class Route {
		  private final String id; 
		  private final Airport source;
		  private final Airport destination;
		  private final int distance; 
		  
		  public Route(String id, Airport source, Airport destination, int distance) {
		    this.id = id;
		    this.source = source;
		    this.destination = destination;
		    this.distance = distance;
		  }
		  
		  public String getId() {
		    return id;
		  }
		  public Airport getDestination() {
		    return destination;
		  }

		  public Airport getSource() {
		    return source;
		  }
		  public int getDistance() {
		    return distance;
		  }

		@Override
		public String toString() {
			return "Route [id=" + id + ", source=" + source + ", destination=" + destination + "]";
		}
}
