package main;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * A lottery with a configurable range, containing methods to add tickets and draw results
 * @author Alessandro Cavicchioli
 * @version 1.0
 */
public class Lottery
{
	private final Random random = new Random();
	private final int lotteryMax;
	private Set<LotteryTicket> lotteryTickets = new HashSet<LotteryTicket>(); 
	
	/**
	 * Initialises a new lottery, which will generate numbers within 0 and the specified value (both inclusive)
	 * @param lotteryMax the max number the lottery will generate
	 */
	public Lottery(int lotteryMax)
	{
		this.lotteryMax = lotteryMax;
	}
	
	/**
	 * Helper method that returns the intersection between the two specified sets
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
	private int getPrize(int matchingNumbersCount)
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
	 * @return a lottery result containing the results of this draw
	 */
	private LotteryResults drawOnce(Set<Integer> winningNumbers)
	{
		LotteryResults results = new LotteryResults();
		for(LotteryTicket ticket : lotteryTickets)
		{	
			int matchingNumberCount = getIntersection(ticket.getNumbers(), winningNumbers).size();
			results.addResult(ticket.getOwnerName(), getPrize(matchingNumberCount));
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
	 * Runs the lottery for the specified amount of times, tallies up the results, and returns the total.<br>
	 * Note that this method does not track ticket count in any way, and will therefore draw the specified amount of times
	 * for every ticket in this lottery.
	 * @param times the amount of draws that should be performed
	 * @return a lottery result containing the total result of all draws
	 */
	public LotteryResults draw(int times)
	{
		LotteryResults totalResult = new LotteryResults();
		for(int i=0; i<times; i++)
		{
			totalResult.accumulateResults(drawOnce(generateWinningNumbers()));
		}
		return totalResult;
	}
}





























