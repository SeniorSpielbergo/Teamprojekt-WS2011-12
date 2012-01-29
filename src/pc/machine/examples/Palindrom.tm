<?xml version="1.0" encoding="utf-8" standalone="no"?>
<machine name="Palindrom" tapes="1" xml-version="3">
    <author>David Wille</author>
    <description>This Turing machine checks whether the given binary number is a palindrom or not.</description>
    <tape type="graphic">
        <name>Input</name>
        <input>
            <symbol>0</symbol>
            <symbol>1</symbol>
            <symbol>0</symbol>
            <symbol>1</symbol>
            <symbol>1</symbol>
            <symbol>0</symbol>
            <symbol>0</symbol>
            <symbol>1</symbol>
            <symbol>1</symbol>
            <symbol>0</symbol>
            <symbol>1</symbol>
            <symbol>0</symbol>
        </input>
    </tape>
    <state final="no" height="50" id="53cf915b-95ed-4b19-a053-4806a1a8cae2" start="yes" width="50" x="100" y="100">
        <name>q_s</name>
    </state>
    <state final="no" height="50" id="44c4d8ea-71a8-4953-a1b5-4a4c874856c7" start="no" width="50" x="300" y="100">
        <name>q_1</name>
    </state>
    <state final="no" height="50" id="5f22e858-397b-48b8-83ae-b3b97ca5e964" start="no" width="50" x="500" y="100">
        <name>q_2</name>
    </state>
    <state final="no" height="50" id="09697b53-f09f-43c8-96f3-0070c4604326" start="no" width="50" x="700" y="100">
        <name>q_3</name>
    </state>
    <state final="no" height="50" id="2b5ce292-9c8c-446e-8490-e8ba200002c8" start="no" width="50" x="300" y="300">
        <name>q_4</name>
    </state>
    <state final="no" height="50" id="05aeea6d-dc5c-4146-9408-cf062d9f4745" start="no" width="50" x="500" y="300">
        <name>q_5</name>
    </state>
    <state final="no" height="50" id="0362392e-34ed-4908-894a-7897742431a5" start="no" width="50" x="700" y="300">
        <name>q_6</name>
    </state>
    <state final="yes" height="50" id="dadc09e6-3588-4996-875e-abdbb5854ae6" start="no" width="50" x="900" y="300">
        <name>q_f</name>
    </state>
    <edge from="53cf915b-95ed-4b19-a053-4806a1a8cae2" labelx="0" labely="5" to="44c4d8ea-71a8-4953-a1b5-4a4c874856c7">
        <transition id="5bd2875e-3590-4395-8f01-b3b4db73dfdb">
            <read>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="44c4d8ea-71a8-4953-a1b5-4a4c874856c7" labelx="0" labely="5" to="5f22e858-397b-48b8-83ae-b3b97ca5e964">
        <transition id="2ffc4076-f88a-4395-a907-9d4ce9941461">
            <read>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="44c4d8ea-71a8-4953-a1b5-4a4c874856c7" labelx="0" labely="-20" to="44c4d8ea-71a8-4953-a1b5-4a4c874856c7">
        <via x="320" y="170"/>
        <transition id="1b145e44-9b79-4022-b072-9cc1b4c85605">
            <read>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>0</symbol>
            </write>
            <action>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="2f01f11c-a1c5-4bae-8583-6595e1ed3afa">
            <read>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="53cf915b-95ed-4b19-a053-4806a1a8cae2" labelx="0" labely="10" to="09697b53-f09f-43c8-96f3-0070c4604326">
        <via x="240" y="60"/>
        <via x="600" y="60"/>
        <transition id="dc8a028b-46ee-4df5-a525-57eec6433a64">
            <read>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="5f22e858-397b-48b8-83ae-b3b97ca5e964" labelx="0" labely="-45" to="05aeea6d-dc5c-4146-9408-cf062d9f4745">
        <transition id="f61bce45-4d3a-4a8b-8146-06e480ae60c1">
            <read>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="5f22e858-397b-48b8-83ae-b3b97ca5e964" labelx="0" labely="-35" to="0362392e-34ed-4908-894a-7897742431a5">
        <transition id="6d139d72-5cb6-4ecb-bac3-24f015f6fb97">
            <read>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="5f22e858-397b-48b8-83ae-b3b97ca5e964" labelx="0" labely="29" to="dadc09e6-3588-4996-875e-abdbb5854ae6">
        <transition id="a2f3e03e-e27a-437d-a270-9ecef8b1f4f6">
            <read>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="53cf915b-95ed-4b19-a053-4806a1a8cae2" labelx="0" labely="-10" to="dadc09e6-3588-4996-875e-abdbb5854ae6">
        <via x="260" y="480"/>
        <via x="850" y="480"/>
        <transition id="fb93cf2f-6bd6-4f22-be79-8fd7c3a2071c">
            <read>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="2b5ce292-9c8c-446e-8490-e8ba200002c8" labelx="0" labely="5" to="05aeea6d-dc5c-4146-9408-cf062d9f4745">
        <transition id="7fa7ce08-9de9-477e-aff7-d1a3a44d1dcb">
            <read>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>0</symbol>
            </write>
            <action>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="2b5ce292-9c8c-446e-8490-e8ba200002c8" labelx="0" labely="-10" to="0362392e-34ed-4908-894a-7897742431a5">
        <via x="420" y="420"/>
        <via x="640" y="420"/>
        <transition id="c50199d8-08f0-4036-acf2-93c5bcda083f">
            <read>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="0362392e-34ed-4908-894a-7897742431a5" labelx="0" labely="-15" to="dadc09e6-3588-4996-875e-abdbb5854ae6">
        <transition id="a33fc000-82ef-4d7b-a149-5c30ba755bc5">
            <read>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>0</symbol>
            </write>
            <action>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="05aeea6d-dc5c-4146-9408-cf062d9f4745" labelx="0" labely="-20" to="05aeea6d-dc5c-4146-9408-cf062d9f4745">
        <via x="540" y="370"/>
        <transition id="c64a73a3-c366-41ae-ab42-3991fea5f2e6">
            <read>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>L</direction>
            </action>
        </transition>
        <transition id="c474189f-f5e6-4e46-a6b0-64e10a66b871">
            <read>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>0</symbol>
            </write>
            <action>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="05aeea6d-dc5c-4146-9408-cf062d9f4745" labelx="0" labely="29" to="53cf915b-95ed-4b19-a053-4806a1a8cae2">
        <transition id="48c6b6a5-902f-453e-9182-15057afb78bf">
            <read>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="2b5ce292-9c8c-446e-8490-e8ba200002c8" labelx="0" labely="10" to="dadc09e6-3588-4996-875e-abdbb5854ae6">
        <via x="400" y="450"/>
        <via x="830" y="450"/>
        <transition id="d7b7a217-061b-44ef-b06c-296e9480a787">
            <read>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="09697b53-f09f-43c8-96f3-0070c4604326" labelx="0" labely="-10" to="2b5ce292-9c8c-446e-8490-e8ba200002c8">
        <via x="1000" y="270"/>
        <via x="1000" y="500"/>
        <via x="890" y="570"/>
        <via x="410" y="570"/>
        <transition id="e8c63899-1aef-4cbc-a24e-1adc041212bc">
            <read>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="09697b53-f09f-43c8-96f3-0070c4604326" labelx="0" labely="20" to="09697b53-f09f-43c8-96f3-0070c4604326">
        <via x="740" y="80"/>
        <transition id="9caa799d-cf91-40cb-86e7-30874315c677">
            <read>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="f07f2b05-e19d-4bf9-bf1d-1fed8ef56170">
            <read>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>0</symbol>
            </write>
            <action>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="0362392e-34ed-4908-894a-7897742431a5" labelx="0" labely="-20" to="0362392e-34ed-4908-894a-7897742431a5">
        <via x="730" y="370"/>
        <transition id="394eee80-d4cb-404d-9086-f26625591b19">
            <read>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>L</direction>
            </action>
        </transition>
        <transition id="b8e41049-8f06-4f6e-83e9-b9a6a0b3d9c2">
            <read>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
</machine>
