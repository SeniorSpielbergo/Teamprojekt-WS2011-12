<?xml version="1.0" encoding="utf-8" standalone="no"?>
<machine name="Decimal Addition" tapes="2" xml-version="3">
    <author>Nils Breyer</author>
    <description>This machine adds two decimal numbers. 

The input has to be on the first tape. The second argument is being copied to the second tape. The value on the first tape is incremented by 1 until the counter (second argument) is zero.

Input: n1#n2
n1 has to be at least as long as n2 (add trailing zeros!)
Output: n1+n2
</description>
    <tape type="graphic">
        <name>Default graphic tape</name>
        <input>
            <symbol>1</symbol>
            <symbol>#</symbol>
            <symbol>2</symbol>
        </input>
    </tape>
    <tape type="graphic">
        <name>Default graphic tape</name>
        <input/>
    </tape>
    <state final="no" height="50" id="9da9938b-9096-4f47-a580-438b9b163ad4" start="yes" width="50" x="200" y="150">
        <name>q0</name>
    </state>
    <state final="no" height="50" id="b77538f0-39dc-4b6c-ae3d-c9644028089e" start="no" width="50" x="450" y="150">
        <name>q1</name>
    </state>
    <state final="no" height="50" id="0b2b09a3-dffa-493d-92b3-795bb7a80803" start="no" width="50" x="900" y="150">
        <name>q2</name>
    </state>
    <state final="no" height="50" id="bdc22518-2d07-4d41-bb85-0f1676cf0402" start="no" width="50" x="400" y="350">
        <name>q3</name>
    </state>
    <state final="no" height="50" id="7fadb63d-b423-4ec7-a0bc-e6475a412c58" start="no" width="50" x="900" y="550">
        <name>q4</name>
    </state>
    <state final="yes" height="50" id="0b22e2ef-b4ff-4c59-8230-543f042d44d1" start="no" width="50" x="150" y="350">
        <name>qF</name>
    </state>
    <state final="no" height="50" id="2048b837-9ff2-4175-bfaf-6f21e45932ac" start="no" width="50" x="150" y="550">
        <name>q5</name>
    </state>
    <state final="no" height="50" id="b916de36-0902-4dc0-862d-6ade6cd2e5ee" start="no" width="50" x="750" y="350">
        <name>q7</name>
    </state>
    <state final="no" height="50" id="2f53b2cb-6ce5-44ec-8689-7c6df7ea3ce0" start="no" width="50" x="150" y="700">
        <name>q7</name>
    </state>
    <state final="no" height="50" id="16739ab6-0000-4255-a5d5-3d70fd2cb380" start="no" width="50" x="750" y="700">
        <name>q8</name>
    </state>
    <edge from="9da9938b-9096-4f47-a580-438b9b163ad4" labelx="0" labely="-80" to="9da9938b-9096-4f47-a580-438b9b163ad4">
        <via x="170" y="190"/>
        <transition id="d8e3fd24-ba95-4b5f-8e8a-dcafaf092e30">
            <read>
                <symbol>0</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="49ad14ef-0d1d-4f7b-a017-0d51f27ba518">
            <read>
                <symbol>1</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="452c0176-1aa2-4f80-8350-f72496741e06">
            <read>
                <symbol>2</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="6db75d72-4af8-484d-8354-27dad595f98e">
            <read>
                <symbol>3</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="9b92ee60-d2f6-4c02-be33-b5dc1e1b20b8">
            <read>
                <symbol>4</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="32cf0240-40a5-4ad0-8082-72ec9d53c5e6">
            <read>
                <symbol>5</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="01a480ff-f4d3-47b9-9569-118b13440850">
            <read>
                <symbol>6</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="9ddfa10a-aa6e-471d-b5b6-7fc1d0e64e09">
            <read>
                <symbol>7</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="46f88d00-1938-4f2a-b63e-6bd71723d55c">
            <read>
                <symbol>8</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="7fd022e4-7b78-4fba-b87c-4c1ff1325b8f">
            <read>
                <symbol>9</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="9da9938b-9096-4f47-a580-438b9b163ad4" labelx="0" labely="15" to="b77538f0-39dc-4b6c-ae3d-c9644028089e">
        <transition id="6141238d-053b-490d-a604-55c07fb6c481">
            <read>
                <symbol>#</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="b77538f0-39dc-4b6c-ae3d-c9644028089e" labelx="0" labely="40" to="b77538f0-39dc-4b6c-ae3d-c9644028089e">
        <via x="460" y="130"/>
        <transition id="30606637-55e5-4104-8f3c-81f56de9e089">
            <read>
                <symbol>0</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>0</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="1fc246e5-4e1c-4861-8797-20a937246b78">
            <read>
                <symbol>1</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="9c86d020-ad7d-40d0-8315-006379500623">
            <read>
                <symbol>2</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>2</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="c4471b8c-5609-4924-9fa4-14204f0f4116">
            <read>
                <symbol>3</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>3</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="7a2ff57f-976c-40ed-978e-a1920ed1372d">
            <read>
                <symbol>4</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>4</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="8f45da47-a2d7-423c-afad-ee4562a3b378">
            <read>
                <symbol>5</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>5</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="d6bc1fdd-5753-482e-bfe3-ccf997e0df62">
            <read>
                <symbol>6</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>6</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="1b3dfb29-382e-45a7-ba00-ec97eb435903">
            <read>
                <symbol>7</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>7</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="5cf93242-0061-41fd-aa49-c58e0b8d6ab1">
            <read>
                <symbol>8</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>8</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="46f95ba5-9ff6-447f-b9ce-52d02fd09af2">
            <read>
                <symbol>9</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>9</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="b77538f0-39dc-4b6c-ae3d-c9644028089e" labelx="0" labely="15" to="0b2b09a3-dffa-493d-92b3-795bb7a80803">
        <transition id="672b935c-e4a0-42b8-a53d-abf84a49458b">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="0b2b09a3-dffa-493d-92b3-795bb7a80803" labelx="0" labely="60" to="0b2b09a3-dffa-493d-92b3-795bb7a80803">
        <via x="970" y="200"/>
        <transition id="7c0a7855-406b-4cf9-be46-204768301508">
            <read>
                <symbol>#</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="0b2b09a3-dffa-493d-92b3-795bb7a80803" labelx="0" labely="-32" to="bdc22518-2d07-4d41-bb85-0f1676cf0402">
        <transition id="3cc39544-9238-4e21-9b61-7b4100be353f">
            <read>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="0b2b09a3-dffa-493d-92b3-795bb7a80803" labelx="0" labely="65" to="7fadb63d-b423-4ec7-a0bc-e6475a412c58">
        <transition id="d87083b7-2730-4525-81f2-fcd6fa05ffbf">
            <read>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="3c7678c0-f91a-4aec-b5fa-ad7dd38f5760">
            <read>
                <symbol>*</symbol>
                <symbol>2</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="5236898d-026e-423e-b6f2-e1327e6c24c0">
            <read>
                <symbol>*</symbol>
                <symbol>3</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="e5cf325e-41bf-40f7-bbd6-6ec63dcd324f">
            <read>
                <symbol>*</symbol>
                <symbol>4</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="2e91b539-912a-4a38-8d02-ba33c7f3c997">
            <read>
                <symbol>*</symbol>
                <symbol>5</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="0be306b2-07d8-4269-9048-c784347bfcae">
            <read>
                <symbol>*</symbol>
                <symbol>6</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="937e3a55-caae-46c0-9ef4-ca62bc3a614f">
            <read>
                <symbol>*</symbol>
                <symbol>7</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="f2a80f07-a771-4f7e-bd26-110cab1cdec5">
            <read>
                <symbol>*</symbol>
                <symbol>8</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="e27472b0-ffd3-4722-81cd-c6a3859a62f2">
            <read>
                <symbol>*</symbol>
                <symbol>9</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="bdc22518-2d07-4d41-bb85-0f1676cf0402" labelx="0" labely="-15" to="0b22e2ef-b4ff-4c59-8230-543f042d44d1">
        <transition id="18e33311-1db0-47b6-8e79-a7b9fad25023">
            <read>
                <symbol>*</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="7fadb63d-b423-4ec7-a0bc-e6475a412c58" labelx="0" labely="-35" to="2048b837-9ff2-4175-bfaf-6f21e45932ac">
        <transition id="aa89d62d-e99f-4c45-9367-c18d962b2168">
            <read>
                <symbol>*</symbol>
                <symbol>9</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>8</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="8db9ec3a-83da-4439-81de-d81dc4e49044">
            <read>
                <symbol>*</symbol>
                <symbol>8</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>7</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="efb80b75-2bd0-4bb3-8030-a50a698a4fad">
            <read>
                <symbol>*</symbol>
                <symbol>7</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>6</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="4abf490d-2886-4072-a2db-673d78fa0f8e">
            <read>
                <symbol>*</symbol>
                <symbol>6</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>5</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="ef79dfc3-565e-49b3-965e-191e5dd95192">
            <read>
                <symbol>*</symbol>
                <symbol>4</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>3</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="129e347a-5e0f-4658-ad73-be2d45811d41">
            <read>
                <symbol>*</symbol>
                <symbol>2</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="f6093a49-2b34-4732-a8c5-1293ce62b548">
            <read>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="db92f6f4-b0aa-4ece-b779-f8cd075a77e1">
            <read>
                <symbol>*</symbol>
                <symbol>5</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>4</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="411827c6-814f-46a1-9f03-dfc1cd6d564d">
            <read>
                <symbol>*</symbol>
                <symbol>3</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>2</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="7fadb63d-b423-4ec7-a0bc-e6475a412c58" labelx="0" labely="6" to="7fadb63d-b423-4ec7-a0bc-e6475a412c58">
        <transition id="82fa005b-bde8-4947-bd83-8a1d7844ec78">
            <read>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>9</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="2048b837-9ff2-4175-bfaf-6f21e45932ac" labelx="0" labely="-60" to="2048b837-9ff2-4175-bfaf-6f21e45932ac">
        <via x="130" y="600"/>
        <transition id="c99b6a49-f9f9-4c71-9a10-7230623a2230">
            <read>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="0e705dba-7287-40ae-8b64-b2b7b99e7996">
            <read>
                <symbol>*</symbol>
                <symbol>2</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="e02be632-a7a9-4cfa-bed7-18476ad08582">
            <read>
                <symbol>*</symbol>
                <symbol>3</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="75086c36-dae6-426a-9617-afbe6d55e22b">
            <read>
                <symbol>*</symbol>
                <symbol>4</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="db06c63c-d208-42da-a176-ba5f1f023ac9">
            <read>
                <symbol>*</symbol>
                <symbol>5</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="6f887e62-197d-4b0b-97d0-a1217b973deb">
            <read>
                <symbol>*</symbol>
                <symbol>6</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="164717ec-8756-4511-aae0-957d788e45ce">
            <read>
                <symbol>*</symbol>
                <symbol>7</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="edf5877a-3664-42ed-852d-1b5f4d5a7de5">
            <read>
                <symbol>*</symbol>
                <symbol>8</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="13093a83-3ddc-4301-9937-3fce18cd52c2">
            <read>
                <symbol>*</symbol>
                <symbol>9</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="e9314ace-47df-4427-91be-cdde3b976f2a">
            <read>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="bdc22518-2d07-4d41-bb85-0f1676cf0402" labelx="0" labely="20" to="bdc22518-2d07-4d41-bb85-0f1676cf0402">
        <via x="440" y="330"/>
        <transition id="d6bf2d43-06b2-4fea-95e7-999c63235dc7">
            <read>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="bdc22518-2d07-4d41-bb85-0f1676cf0402" labelx="0" labely="25" to="b916de36-0902-4dc0-862d-6ade6cd2e5ee">
        <transition id="ade08038-18c0-4176-8185-92bd61ab88ba">
            <read>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="fb362923-1df4-4515-ac00-ddddea984f48">
            <read>
                <symbol>*</symbol>
                <symbol>2</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="c1899e5e-12cb-4621-83a7-6253017ac825">
            <read>
                <symbol>*</symbol>
                <symbol>3</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="79d112f8-25dd-4fa7-900f-dcc8b56c160b">
            <read>
                <symbol>*</symbol>
                <symbol>4</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="61de0c70-aa59-4963-9340-1789e132875e">
            <read>
                <symbol>*</symbol>
                <symbol>5</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="f60ae646-a7ea-4363-b7fb-c5a15257303c">
            <read>
                <symbol>*</symbol>
                <symbol>6</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="02968dd4-afa1-4d17-afe6-bb29257fa9d3">
            <read>
                <symbol>*</symbol>
                <symbol>7</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="c31b0b78-e325-4129-a3be-a3ec82ae4fb0">
            <read>
                <symbol>*</symbol>
                <symbol>8</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="5acaa5dd-1927-47de-9c55-41494edb4e84">
            <read>
                <symbol>*</symbol>
                <symbol>9</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="b916de36-0902-4dc0-862d-6ade6cd2e5ee" labelx="0" labely="30" to="b916de36-0902-4dc0-862d-6ade6cd2e5ee">
        <via x="790" y="330"/>
        <transition id="65fcc74a-5062-4dd9-a625-b5248ec73046">
            <read>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="f1f73fef-c403-4455-90ba-64ac6b06ddcf">
            <read>
                <symbol>*</symbol>
                <symbol>2</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="b11427a7-8d33-4c89-a0fd-7b9f4f797f16">
            <read>
                <symbol>*</symbol>
                <symbol>3</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="ed904111-75ea-4b0c-920c-7c296a537c7e">
            <read>
                <symbol>*</symbol>
                <symbol>4</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="de88cd1d-7c1a-41b2-b418-c7441dcf8410">
            <read>
                <symbol>*</symbol>
                <symbol>5</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="db39cd42-3f3a-4070-9b62-bb48aef9d27e">
            <read>
                <symbol>*</symbol>
                <symbol>6</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="334872c5-9ce1-4540-89a7-1053902cf63a">
            <read>
                <symbol>*</symbol>
                <symbol>7</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="c502e147-45a3-4254-bfcf-5ad3a15514fe">
            <read>
                <symbol>*</symbol>
                <symbol>8</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="31b68608-7946-4f2d-981a-04be61068fc4">
            <read>
                <symbol>*</symbol>
                <symbol>9</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="e425df14-7486-4571-a2c2-3271a055ba7e">
            <read>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="b916de36-0902-4dc0-862d-6ade6cd2e5ee" labelx="0" labely="0" to="7fadb63d-b423-4ec7-a0bc-e6475a412c58">
        <transition id="d2d8c6e2-5306-4790-ac25-2ebf78106575">
            <read>
                <symbol>*</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="2f53b2cb-6ce5-44ec-8689-7c6df7ea3ce0" labelx="0" labely="25" to="16739ab6-0000-4255-a5d5-3d70fd2cb380">
        <transition id="a2552611-2466-457c-84fd-3dae8fcffabb">
            <read>
                <symbol>1</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>2</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="f705dde6-5cae-437a-9152-7d80b9925aeb">
            <read>
                <symbol>2</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>3</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="faecf643-89ae-475d-8c10-5a328c5f7a7b">
            <read>
                <symbol>3</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>4</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="fe38dd4e-22e5-4488-80bd-9d09b730cd27">
            <read>
                <symbol>4</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>5</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="bbc1bb79-5a4a-44f8-9e57-8c7e2da32621">
            <read>
                <symbol>5</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>6</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="4dd07e29-785f-4f24-ad8a-da2fca077905">
            <read>
                <symbol>6</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>7</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="889a0307-b48d-4b93-b7cc-6bbb8288f773">
            <read>
                <symbol>7</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>8</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="62817f48-edf9-4ca0-a0b9-bb93e548dfd0">
            <read>
                <symbol>8</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>9</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="4defa725-b0fd-407f-8395-d7a5cd7eb562">
            <read>
                <symbol>0</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>1</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="2f53b2cb-6ce5-44ec-8689-7c6df7ea3ce0" labelx="0" labely="-60" to="2f53b2cb-6ce5-44ec-8689-7c6df7ea3ce0">
        <via x="130" y="750"/>
        <transition id="f6c65ea7-f57f-4a07-8121-c11f035eb672">
            <read>
                <symbol>9</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>0</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="16739ab6-0000-4255-a5d5-3d70fd2cb380" labelx="0" labely="30" to="16739ab6-0000-4255-a5d5-3d70fd2cb380">
        <via x="750" y="680"/>
        <transition id="02b60ac8-5d31-465b-8982-003eb1e1dd77">
            <read>
                <symbol>1</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="b01b2ea0-a1e4-42fa-8b49-7b3bfdd3bbba">
            <read>
                <symbol>2</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="e23e6e28-08e1-42c3-9268-1a53af863fdf">
            <read>
                <symbol>3</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="d44455bc-fe7f-454a-b732-de4622bbce4e">
            <read>
                <symbol>4</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="173eae3c-a362-49ca-9c24-d520eff6b8fa">
            <read>
                <symbol>5</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="9d54170d-5556-4e47-8365-b50a6e59740a">
            <read>
                <symbol>6</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="9589edf0-faa9-40ff-b438-159aa610b8dc">
            <read>
                <symbol>7</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="72172735-5852-4ee3-9a13-e3898fee7d01">
            <read>
                <symbol>8</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="c45532d7-e781-41ee-99af-626ec245bc9d">
            <read>
                <symbol>9</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="7465645f-75e7-46cf-89d2-062536b0cf9c">
            <read>
                <symbol>0</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="16739ab6-0000-4255-a5d5-3d70fd2cb380" labelx="-1" labely="12" to="0b2b09a3-dffa-493d-92b3-795bb7a80803">
        <via x="1080" y="720"/>
        <via x="1080" y="310"/>
        <transition id="f24ad2e8-3ee9-4de1-a034-ce231119e72d">
            <read>
                <symbol>#</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="2048b837-9ff2-4175-bfaf-6f21e45932ac" labelx="0" labely="-55" to="2f53b2cb-6ce5-44ec-8689-7c6df7ea3ce0">
        <transition id="b62f798b-833e-4bd6-b868-90409c55f8e0">
            <read>
                <symbol>*</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <textbox height="30" width="290" x="40" y="60">
        <text>Copy second argument to second tape</text>
    </textbox>
    <textbox height="30" width="180" x="40" y="260">
        <text>Check if zero</text>
    </textbox>
    <textbox height="30" width="220" x="40" y="460">
        <text>Second argument minus 1</text>
    </textbox>
    <textbox height="60" width="210" x="740" y="10">
        <text>Please don't simulate this machine 
with the LEGO-Tape!</text>
    </textbox>
    <textbox height="30" width="170" x="200" y="620">
        <text>Add 1 to first arg</text>
    </textbox>
    <textbox height="50" width="420" x="620" y="80">
        <text>Input: n1#n2
n1 has to be at least as long as n2 (add trailing zeros!)
Output: n1+n2
</text>
    </textbox>
</machine>
