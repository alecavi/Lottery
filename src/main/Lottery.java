/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * A class meant to simulate a lottery and announce a winner
 * @author Alessandro Cavicchioli
 * @version 1.0
 */
public class Lottery
{
	private final Random random = new Random();
	private final int lotteryMax;
	private List<LotteryTicket> lotteryTickets = new ArrayList<LotteryTicket>(); 
	
	/**
	 * Initialises a new lottery, which will generate numbers within 0 and the specified value (both inclusive)
	 * @param lotteryMax the max number the lottery will generate
	 */
	public Lottery(int lotteryMax)
	{
		this.lotteryMax = lotteryMax;
	}
	
	/**
	 * Returns the intersection between the two specified sets
	 * @param set1 the first set
	 * @param set2 the second set
	 * @return the intersection of {@code set1} and {@code set2}
	 */
	private static Set<Integer> getIntersection(Set<Integer> set1, Set<Integer> set2)
	{
		set1.retainAll(set2); //Set1 is now the intersection of set1 and set2
		return set1;
	}
	
	/**
	 * Returns the amount of money won for the specified amount of numbers found both on a ticket and in the winning numbers
	 * @param matchingNumbersCount the amount of matching numbers
	 * @return the prize money (in pounds)
	 */
	private static int getPrize(int matchingNumbersCount)
	{
		switch(matchingNumbersCount)
		{
		case 3:
			return 25;
		case 4:
			return 100;
		case 5:
			return 1000;
		case 6:
			return 1000000;
		default:
			return 0;
		}
	}
	
	/**
	 * Adds the winnings in the second map to those in the first map. Note that this is done in place, and
	 * will modify the map the winnings are added to
	 * @param totalResults the map to be added to
	 * @param newResults the map with the winnings to add
	 * @return the map the winnings were added to
	 */
	private static Map<String, Integer> addWinnings(Map<String, Integer> totalResults, Map<String, Integer> newResults)
	{	
		for(Map.Entry<String, Integer> pair : newResults.entrySet())
		{
			String ownerName = pair.getKey();
			Integer totalWinnings = totalResults.get(ownerName);
			Integer newWinnings = pair.getValue();
			
			if(!totalResults.containsKey(ownerName)) //This person hasn't played yet
			{
				totalResults.put(ownerName, 0); //Therefore they haven't won anything yet
			}
			totalResults.put(ownerName, totalWinnings + newWinnings); //Add the new winnings to their old winnings
		}
		return totalResults;
	}
	
	/**
	 * Generates six random numbers between 0 and the max value (both inclusive) and returns a set containing them
	 * @return a set containing the random numbers that were generated
	 */
	private Set<Integer> generateWinningNumbers()
	{
		Set<Integer> winningNumbers = new HashSet<Integer>();
		
		int i=0;
		while(i<6)
		{
			boolean added = winningNumbers.add(random.nextInt(lotteryMax + 1));
			if(added) i++;
		}
		return winningNumbers;
	}
	
	/**
	 * Runs one draw of the lottery, using the specified set of winning numbers
	 * @param winningNumbers the set of winning numbers
	 * @return a map associating the names of the owners of the tickets with the amount of money they won
	 */
	private Map<String, Integer> drawOnce(Set<Integer> winningNumbers)
	{
		Map<String, Integer> results = new HashMap<String, Integer>();
		for(LotteryTicket ticket : lotteryTickets)
		{	
			int matchingNumberCount = getIntersection(ticket.getNumbers(), winningNumbers).size();
			results.put(ticket.getOwnerName(), getPrize(matchingNumberCount));
		}
		return results;
	}
	
	
	
	/**
	 * Adds a ticket to this lottery
	 * @param ticket the ticket to add
	 */
	public void addTicket(LotteryTicket ticket)
	{
		lotteryTickets.add(ticket);
	}
	
	/**
	 * Runs the lottery for the specified amount of times, tallies up the results, and returns the results <br>
	 * Note that this method does not track ticket count in any way, and will therefore draw the specified amount of times
	 * for every ticket in this lottery.
	 * @param times the amount of draws that should be performed
	 * @return a map associating the names of the owners of the tickets with the amount of money they won
	 */
	public Map<String, Integer> draw(int times)
	{
		Map<String, Integer> totalResults = new HashMap<String, Integer>();
		for(int i=0; i<times; i++)
		{
			Map<String, Integer> tempResults = drawOnce(generateWinningNumbers());
			addWinnings(totalResults, tempResults);
		}
		return totalResults;
	}
}





























