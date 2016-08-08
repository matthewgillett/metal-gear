package org.finra.metal.gear.Infrastructure;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.services.databasemigrationservice.AWSDatabaseMigrationServiceClient;
import com.amazonaws.services.rds.AmazonRDSClient;

/**
 * Create AWS DMS client objects using a proxy if specified. A proxy is required to run the code locally
 * using eclipse or on local host.
 */
public class AwsClientFactory {
		
	public AmazonRDSClient createRDSClient()
	{
		AmazonRDSClient amazonRDSClient = null;
		if ((System.getProperty("aws.proxy.host") != null && System.getProperty("aws.proxy.port") != null))
		{
			ClientConfiguration config = new ClientConfiguration();
			
			config.setProxyHost(System.getProperty("aws.proxy.host"));
			config.setProxyPort(Integer.parseInt(System.getProperty("aws.proxy.port")));
			amazonRDSClient = new AmazonRDSClient(config);
		}
		else	
		{
			amazonRDSClient = new AmazonRDSClient();
		}
		
		return amazonRDSClient;
	}
	
}
