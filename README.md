# Java-JSON-Integration-Lab (using Jackson)

This project demonstrates how to integrate the **Jackson JSON Processor** into a Java environment. Use the following steps to configure the library dependencies in **IntelliJ IDEA**.

## Dependency Configuration Steps
1. **Prepare Source Files:** Ensure the `.jar` files from the `lib` directory are accessible on your local machine.
2. **Open Project Menu:** Press `Alt` + `\` (or click the **Menu** icon next to the IntelliJ Logo).
3. **Access Project Structure:** Use the shortcut `Ctrl` + `Alt` + `Shift` + `S` (or navigate to **File > Project Structure...**).
4. **Navigate to Dependencies:** Under **Project Settings**, select **Modules**, then click the **Dependencies** tab.
5. **Add New Library:** Click the **+** (Plus) button and select **1. JARs or Directories**.
6. **Import Jackson Jars:** Navigate to your files, hold `Shift` to multi-select all Jackson `.jar` files, and click **OK**.
7. **Verify & Execute:** Ensure the jars are listed and checked. You are now ready to perform JSON serialization and deserialization!

---
*Note: This configuration is essential for projects requiring data persistence or API interaction.*
