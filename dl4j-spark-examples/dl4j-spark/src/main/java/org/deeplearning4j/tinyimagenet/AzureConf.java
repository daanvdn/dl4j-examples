package org.deeplearning4j.tinyimagenet;

import org.apache.hadoop.conf.Configuration;

public class AzureConf {
	public final static String AZURE_STORAGE_PATH_TEMPLATE = "wasbs://%s@%s.blob.core.windows.net";
	public final static String AZURE_FOLDER_PATH_TEMPLATE = AZURE_STORAGE_PATH_TEMPLATE + "/%s";

	private static Configuration configuration;

	private AzureConf() {

	}

	public static Configuration getHadoopConfiguration(String accountName, String accountKey) {
		if (configuration != null) {
			return configuration;
		} else {

			configuration = new Configuration();
			configuration.set("fs.wasb.impl", "org.apache.hadoop.fs.azure.NativeAzureFileSystem");
			configuration.set("fs.AbstractFileSystem.wasb.impl", "org.apache.hadoop.fs.azure.Wasb");
			configuration.set("fs.wasbs.impl", "org.apache.hadoop.fs.azure.NativeAzureFileSystem");
			configuration.set("fs.AbstractFileSystem.wasbs.impl", "org.apache.hadoop.fs.azure.Wasb");
			configuration.set("fs.azure", "org.apache.hadoop.fs.azure.NativeAzureFileSystem");
			configuration.set("fs.azure.account.key." + accountName + ".blob.core.windows.net", accountKey);
			return configuration;
		}
	}

	public static String getFolderPathInContainer(String accountName, String containerName, String folderName) {
		return String.format(AZURE_FOLDER_PATH_TEMPLATE, containerName, accountName, folderName);
	}

	public static String getContainerPath(String accountName, String containerName) {
		return String.format(AZURE_STORAGE_PATH_TEMPLATE, containerName, accountName);
	}

}
