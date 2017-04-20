import java.util.ArrayList;
import java.util.Iterator;
/**
 *  A class for implementing and managing ChargerMail, a simple mail client
 * @author Tevon Walker
 * 
 */
public class MailSystem {

	/**
	 * Default constructor creates empty system
	 */
	
	public MailSystem(){
		userQueue = new ArrayList<User>();
		accountQueue = new ArrayList<Account>();
	}
	
	/**
	 * Checks to see if userName is already taken
	 * @param userName Username to test 
	 * @return a boolean value is returned depending on whether this username is unique or not
	 */
	
	public boolean isNewUserUnique(String userName){
		
		Iterator<User> list = userQueue.iterator();
		while (list.hasNext())
		{
			User temp = list.next();
			if(temp.getUserName().equals(userName))
			{
				
				return false;
			}
		}
		 return true;
	}
	
	/**
	 * Attempts to create a new user within system
	 * @param userName the desired user name of the new user
	 */
	
	public void addNewUser(User newUser){
		
			if (newUser == null)
				return;
			
			userQueue.add((newUser));		
	}
	
	/**
	 * Deletes user from System
	 * @param userName name of user to delete
	 */
	
	public void deleteUser(String userName){
		
		//remove from system userQueue
		Iterator<User> list = userQueue.iterator();
		while (list.hasNext())
		{
			if(list.next().getUserName().equals(userName))
			{
				list.remove();
			}
		}
		
		
		//remove user accounts from accountQueue
		
		Iterator<Account> listb = accountQueue.iterator();
		while (listb.hasNext())
		{
			
			if(listb.next().getUsername().equals(userName))
			{
				listb.remove();
			}
		
		}
		
		
	}
	
	/**
	 * Transfer messages among users
	 * @param msg Message to be sent
	 */
	
	public void transferMessage(String aNameSender,String aNameReceiver,Message msg,int type){
		
		if (msg == null)
			return;
		
		if(type == 0) //send to recipient inbox
		{
			Iterator<Account> list = accountQueue.iterator();
			while (list.hasNext())
			{
				Account temp = list.next();
				
				if(temp.getAccountName().equals(aNameReceiver))
				{
					temp.receiveMessage(type, msg);
				}
			}
			
		}
		
		else if (type == 1) //send to sent box
		{
			Iterator<Account> list = accountQueue.iterator();
			while (list.hasNext())
			{
				Account temp = list.next();
				
				if(temp.getAccountName().equals(aNameSender))
				{
					temp.receiveMessage(type, msg);
				}
			}
			
		}
		
		
		
	}
	
		
	/**
	 * Return number of users in Mailsystem
	 * @return the integer number of users
	 */
	public int getNumUsers()
	{
		return userQueue.size();
	}
	
	/**
	 * Adds account to particular user in system
	 * @param userName Name of user to add account to
	 * @param newAccount the new account to add
	 * @param local is the account local or remote?
	 */
	public void addAccount(String userName,Account newAccount,boolean local){

		if (newAccount == null)
			return;
		
		Iterator<User> list = userQueue.iterator();
		while (list.hasNext())
		{
			User temp = list.next();
			if(temp.getUserName().equals(userName))
			{
				temp.addAccount(newAccount,local);
				accountQueue.add(newAccount);
			}
		}
		
		
	}
	/**
	 * Removes account from particular user in system
	 * @param userName name of user to remove account from
	 * @param account the account to delete
	 * @param local is the account local or remote?
	 */
	public void deleteAccount(String userName,String account,boolean local){
		
		
		Iterator<User> list = userQueue.iterator();
		while (list.hasNext())
		{
			User temp = list.next();
			if(temp.getUserName().equals(userName))
			{
				temp.deleteAccount(account,local);
			}
		}
		
		Iterator<Account> listA = accountQueue.iterator();
		
		int index = 0;
		
		while (listA.hasNext())
		{
			Account temp = listA.next();
			if(temp.getAccountName().equals(account))
			{
				break;
			}
			index++;
		}
		
		accountQueue.remove(index);
		
		}
	
	
		
		
	
	
/**
 * Function to fetch an array of messages from a mailbox
 * @param aName account name
 * @param type inbox, sent, or trash box?
 * @return a cloned array of messages of a particular mailbox
 */
	public ArrayList<Message> getMessages(String aName,int type)
	{
		Iterator<Account> list = accountQueue.iterator();
		while (list.hasNext())
		{
			Account temp = list.next();
			if(temp.getAccountName().equals(aName))
			{
				return temp.getMailboxMessages(type);
			}
		}
		
		return null;
		
	}
	
	/**
	 * Function to determine whether an account already exists in the system
	 * @param aName the name of the account to test
	 * @return true/false if the account already exists
	 */
	public boolean accountExists(String aName)
	{
		Iterator<Account> list = accountQueue.iterator();
		Account temp;
		while (list.hasNext())
		{
			temp = list.next();
			if(temp.getAccountName().equals(aName))
			{
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * Delete message from a mailbox
	 * @param account name of account to delete message from
	 * @param mess title of the message
	 * @param type which box is this message in?
	 */
	public void deleteMessage(String account, String mess,int type)
	{
		Iterator<Account> list = accountQueue.iterator();
		Account temp;
		while (list.hasNext())
		{
			temp = list.next();
			if(temp.getAccountName().equals(account))
			{
				temp.removeMessage(mess, type);
			}
		}
		
		
		
	}
	
	private ArrayList<User> userQueue;
	private ArrayList<Account> accountQueue;

}
