package xmpp.test.client;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.debugger.SmackDebugger;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Type;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.packet.XMPPError.Condition;


public class XMPPConnection {
  
	private static final String TAG = "sxmppcon";
	private boolean anonymous = false;
	private boolean authenticated = false;
	private Collection<String> compressionMethods;
	private boolean connected = false;
	String connectionID = null;
	private Object mutex = new Object();
	//PacketReader packetReader;
	//PacketWriter packetWriter;
	//private ParsingExceptionCallback parsingExceptionCallback = SmackConfiguration.getDefaultParsingExceptionCallback();
	//Roster roster = null;
    private boolean serverAckdCompression = false;
	Socket socket;
    private volatile boolean socketClosed = false;
	private String user = null;
    private boolean usingTLS = false;
	private boolean wasAuthenticated = false;	
	
	
	
	
}
