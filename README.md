# 💻 xHwid
️A Java library to generate unique identifiers for user devices.

## 🔎 How to use
`System.out.println("HWID: " + new HardwareIdentification().getIdentifier());`

## 📝How to change identifier format
    HardwareIdentification hwid = new HardwareIdentification();
    hwid.setIdentifierFormat("%username%|%osname%|%pcname%|%osarch%|%cpumodel%"); // Example that uses all the placeholders
