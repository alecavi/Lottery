package main;

import java.util.HashMap;
import java.util.Map;

/**
 * The results of a lottery. Contains methods to get a specific player's winnings and add new winnings to the currently recorded ones
 * @author Alessandro Cavicchioli
 * @version 1.0
 */
public class LotteryResults
{
	private Map<String, Integer> results = new HashMap<String, Integer>();
	
	/**
	 * Returns the total amount of money won by the specified player
	 * @param playerName the name of the player
	 * @return the amount of money they won
	 */
	public Integer getWinnings(String playerName)
	{
		return results.get(playerName);
	}
	
	/**
	 * Increases the winnings of the specified player by the specified amount. If there is no such player, one will be created
	 * and their winnings will be set to the specified amount
	 * @param playerName the name of the player
	 * @param newWinnings the amount of money to add
	 */
	public void addResult(String playerName, Integer newWinnings)
	{
		Integer currentWinnings = results.get(playerName);
		if(currentWinnings == null) currentWinnings = 0; //null winnings mean that this player hasn't won anything yet
		results.put(playerName, currentWinnings + newWinnings);
	}
	
	/**
	 * Merges the two lottery results, increasing the winnings for each person in this object by their winnings in the other object.
	 * If the other object contains people that this one doesn't, they will be added to this object with the same winnings as they have
	 * in the other one
	 * @param newResult the result to add to this one
	 */
	public void accumulateResults(LotteryResults newResults)
	{
		for(Map.Entry<String, Integer> newEntry : newResults.results.entrySet())
		{
			addResult(newEntry.getKey(), newEntry.getValue());
		}
	}
}
