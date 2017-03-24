package xmpp.test.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Mode;
import org.jivesoftware.smack.packet.Presence.Type;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.ping.PingManager;


public class XMPPUtil {
	
	
	  public static XMPPConnection connection;
	  public static boolean isNetConnect = false;
	  private static long lastSuccessCallTime = -1L;
	  public static String mac;
	  public static String m_uuid;
	  private static XMPPUtil xmppUtil = new XMPPUtil();
	  //private Context context;
	  boolean isConnecting = false;
	  private long lastConnectTime;
	  private long nowTime;
	  
	  
	  public static XMPPConnection getConnection()
	  {
	    synchronized (xmppUtil)
	    {
	      XMPPConnection localXMPPConnection = connection;
	      return localXMPPConnection;
	    }
	  }	
	  
	  public static boolean deleteAccount(XMPPConnection paramXMPPConnection)
	  {
	    try
	    {
	      paramXMPPConnection.getAccountManager().deleteAccount();
	      return true;
	    }
	    catch (Exception localException) {
	    	localException.printStackTrace();
	    }
	    return false;
	  }
	  
	  
	  public void SendBCMsg(String paramString1, String paramString2, String paramString3, String paramString4)
	  {
	    try
	    {
	      Message localMessage = new Message();
	      localMessage.setBody(paramString3);
	      localMessage.setTo(paramString4 + "@broadcast.xjtu");
	      connection.sendPacket(localMessage);
	      return;
	    }
	    catch (Exception localException)
	    {
	      localException.printStackTrace();
	    }
	  }
	  
	  
//	  public void SendMsg(String paramString1, String paramString2, String paramString3)
//	  {
//	    try
//	    {
//	      connection.getChatManager().createChat("admin@xjtu", new XMPPUtil.2(this)).sendMessage(paramString3);
//	      return;
//	    }
//	    catch (XMPPException localXMPPException)
//	    {
//	      localXMPPException.printStackTrace();
//	    }
//	  }
	  
	  
	  public boolean conServer()
	  {
	    return true;
	  }
	  
	  
	  /*
	   * This function will connect the server*/
	  public void connectionServer()
	  {
	    if (this.isConnecting)
	    {
	      System.out.println(" [ ConnectionServer Debug] return directly because of connecting!");
	      
	      return;
	    }
	    synchronized (xmppUtil)
	    {
	      this.isConnecting = true;
	      try
	      {
	        if ((connection == null) || (!connection.isConnected()))
	        {
	          ConnectionConfiguration localConnectionConfiguration = new ConnectionConfiguration("123.103.13.252", 5222);
	          localConnectionConfiguration.setSASLAuthenticationEnabled(true);
	          localConnectionConfiguration.setReconnectionAllowed(true);
	          connection = new XMPPConnection(localConnectionConfiguration);
	          System.out.println(" [ ConnectionServer Debug]  start connect to the XMPP Server");
	          connection.connect();
	          System.out.println(" [ ConnectionServer Debug]  finish connect the XMPP Server");
	        }
	        if (!connection.isAuthenticated())
	        {
	          //m_uuid = "55e8cc69-6697-44a4-8e0d-55bde440c1b7";
	          m_uuid = "21561218-dee4-4f99-b730-a2496278ffb2";  
	          XMPPConnection localXMPPConnection = connection;
	          String str = m_uuid;
	          localXMPPConnection.login(m_uuid, m_uuid, "android");
	          System.out.println(" [ ConnectionServer Inf ]  i have logined into the server");
	        }
	        //EventBus.getDefault().postSticky(new XmppReloginEvent());
	      }
	      catch (XMPPException localXMPPException)
	      {
	        for (;;)
	        {
	          localXMPPException.printStackTrace();
	          this.isConnecting = false;
	        }
	      }
	      catch (Exception localException)
	      {
	        for (;;)
	        {
	          localException.printStackTrace();
	          this.isConnecting = false;
	        }
	      }
	      finally
	      {
	        this.isConnecting = false;
	      }
	      return;
	    }
	  }
	  
	  public boolean isAuthenticated()
	  {
	    if (connection == null) {
	      return false;
	    }
	    return connection.isAuthenticated();
	  }
	  
	  public boolean isConnected()
	  {
	    if (connection == null) {
	      return false;
	    }
	    return connection.isConnected();
	  }
	  
	  
	  public boolean isLogin()
	  {
	    return (connection != null) && (connection.isConnected()) && (connection.isAuthenticated());
	  }
	  
	  
	  public boolean sendMessage(String paramString1, String paramString2)
	  {
//	    if ((connection == null) || (!connection.isAuthenticated()))
//	    {
//	      mac = NetUtil.getMacAddress(this.context);
//	      if (!login(mac, mac)) {
//	        return false;
//	      }
//	    }
	    Chat localChat = connection.getChatManager().createChat(paramString1, new MessageListener(){
	    	public void processMessage(Chat chat, Message message){
	    		System.out.println(" [ MessageListener Inf]  Recieved the Message from the server is: " + message);
	    	};
	    });
	    try
	    {
	      System.out.println(" [ sendMessage Inf]  i will send the message to the XMPP server: " + paramString2);
	      localChat.sendMessage(paramString2);
	      return true;
	    }
	    catch (XMPPException localXMPPException)
	    {
	      localXMPPException.printStackTrace();
	    }
	    return false;
	  }

	  
	  public static void closeConnection(){
		  try {
			  if (XMPPUtil.connection != null) {
				  XMPPUtil.connection.disconnect();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	  }
	  	  
	  
	  public static void main(String[] paramArrayOfString) {
		  
		  /*close all*/
		  //String mString = "encryption:f0wpC5bc6TVNVxqZ1Kui6cJcrK6H1P1AYYrQ4ZZZMcTESHUWoJjbYH3M/U7lkutB5oJOV1stRs2N5FNxotTsM8O/6LuxkXkfDIvRB4tL56U=";
		  /*open3*/
		  String mString = "encryption:f0wpC5bc6TVNVxqZ1Kui6cJcrK6H1P1AYYrQ4ZZZMcTESHUWoJjbYH3M/U7lkutBDi8B+ibe52+U6XqPnV9LndvWnqVaxnpQ/T0+aoAre7o=";
		  /*open all*/
		  //String mString = "encryption:f0wpC5bc6TVNVxqZ1Kui6cJcrK6H1P1AYYrQ4ZZZMcTESHUWoJjbYH3M/U7lkutB56dZ1qFMhtqYmDFNL5w7wUXnKTGi9B6/6fEIMhLACEE=";
		  
		  boolean m_result;
		  
		  XMPPUtil mXmppUtil = new XMPPUtil();
		  mXmppUtil.connectionServer();
		  
		  /* This fragment will send the console direction to the devices*/
		  m_result = mXmppUtil.sendMessage("28-d9-8a-8c-2a-21@kankun-smartplug.com", mString);
		  if(m_result){
			  System.out.println(" [ main Inf ] This device all ports is closed!");
			  mXmppUtil.closeConnection();
		  }else{
			  System.out.println(" [ main Error ] This Command do not run at this moment");
		  }
		  
		  
		  
	  }
	  
}
