''Arguements : -
XMLPath = WScript.Arguments(0)
ClassNameValue  = WScript.Arguments(1)

''Update XML :-
Set objXMLDoc = CreateObject("Microsoft.XMLDOM") 
objXMLDoc.async = false 
objXMLDoc.load(XMLPath)
WScript.Sleep 2000
Wscript.Echo objXMLDoc.XML
Set Root = objXMLDoc.documentElement 
Set NodeList = Root.getElementsByTagName("class")
WScript.Sleep 2000
Wscript.Echo NodeList.length
NodeList(0).setattribute("name") = ClassNameValue
objXMLDoc.save XMLPath
