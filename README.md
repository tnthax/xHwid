# ð» xHwid
ï¸A Java library to generate unique identifiers for user devices.

## ð How to import with Gradle
    repositories {
        maven { url 'https://jitpack.io' }
    }
    dependencies {
		implementation 'com.github.tnthax:xHwid:v1.0.0'
	}
I'm not going to include Maven instructions, go figure it out yourself. :-)

## ð How to use
`System.out.println("HWID: " + new HardwareIdentification().getIdentifier());`

## ðHow to change identifier format
    HardwareIdentification hwid = new HardwareIdentification();
    hwid.setIdentifierFormat("%username%|%osname%|%pcname%|%osarch%|%cpumodel%"); // Example that uses all the placeholders
