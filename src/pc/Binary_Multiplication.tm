<?xml version="1.0" encoding="utf-8" standalone="no"?>
<machine name="Binary Multiplication" tapes="3" xml-version="3">
    <tape type="graphic">
        <name>Input1 / Output</name>
        <input>
            <symbol>0</symbol>
            <symbol>1</symbol>
            <symbol>0</symbol>
            <symbol>1</symbol>
        </input>
    </tape>
    <tape type="graphic">
        <name>Input2</name>
        <input>
            <symbol>1</symbol>
            <symbol>0</symbol>
        </input>
    </tape>
    <tape type="graphic">
        <name>Memory</name>
        <input/>
    </tape>
    <state final="no" height="50" id="95a4b6aa-975f-49c9-9dd3-366150cccb68" start="yes" width="50" x="100" y="200">
        <name>q_s</name>
    </state>
    <state final="no" height="50" id="33d74080-61b8-4ff7-a8c4-5a88cb810d35" start="no" width="50" x="700" y="200">
        <name>q_1</name>
    </state>
    <state final="no" height="50" id="1b469c68-0292-451a-ae1e-2353687d66dd" start="no" width="50" x="950" y="200">
        <name>q_2</name>
    </state>
    <state final="no" height="50" id="dfc99d3b-bc06-4d94-8425-e9c2b0f74991" start="no" width="50" x="700" y="350">
        <name>q_3</name>
    </state>
    <state final="no" height="50" id="e3ab2cdb-d1e2-4a18-a530-3d2cbe448ff9" start="no" width="50" x="700" y="500">
        <name>q_4</name>
    </state>
    <state final="no" height="50" id="db734b0a-6034-4979-9e75-62e3398710d2" start="no" width="50" x="500" y="200">
        <name>q_5</name>
    </state>
    <state final="no" height="50" id="92f333ee-0318-407b-927e-036dc48e68ec" start="no" width="50" x="300" y="200">
        <name>q_6</name>
    </state>
    <state final="yes" height="50" id="8f51ce8a-1183-47aa-8544-f0983e6a88dd" start="no" width="50" x="300" y="650">
        <name>q_f</name>
    </state>
    <state final="no" height="50" id="962ce74a-5fe2-4e78-a32e-565df33ef7fd" start="no" width="50" x="700" y="650">
        <name>q_7</name>
    </state>
    <edge from="33d74080-61b8-4ff7-a8c4-5a88cb810d35" labelx="0" labely="30" to="33d74080-61b8-4ff7-a8c4-5a88cb810d35">
        <via x="740" y="180"/>
        <transition id="501bb7c8-0b88-4499-9916-aa91cdca893f">
            <read>
                <symbol>0</symbol>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>0</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
        <transition id="ea09b218-c949-4732-b52f-6c68b055a5de">
            <read>
                <symbol>1</symbol>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>1</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
        <transition id="a63366f6-67fa-49e2-8865-635256458f2e">
            <read>
                <symbol>0</symbol>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>1</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="33d74080-61b8-4ff7-a8c4-5a88cb810d35" labelx="0" labely="-20" to="1b469c68-0292-451a-ae1e-2353687d66dd">
        <via x="780" y="180"/>
        <via x="910" y="180"/>
        <transition id="4474fb23-7dfc-4088-926e-3ca9268dd7da">
            <read>
                <symbol>1</symbol>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>0</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="1b469c68-0292-451a-ae1e-2353687d66dd" labelx="0" labely="30" to="1b469c68-0292-451a-ae1e-2353687d66dd">
        <via x="990" y="180"/>
        <transition id="7baab084-ad2d-459f-98ca-aa3bd08b8337">
            <read>
                <symbol>1</symbol>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>1</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
        <transition id="5f409b0f-c9fc-4917-a050-b7e7018ecece">
            <read>
                <symbol>1</symbol>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>0</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
        <transition id="87c5fed3-49bf-4a6e-b04d-2bedb0234d78">
            <read>
                <symbol>0</symbol>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>0</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="1b469c68-0292-451a-ae1e-2353687d66dd" labelx="0" labely="13" to="33d74080-61b8-4ff7-a8c4-5a88cb810d35">
        <via x="920" y="260"/>
        <via x="780" y="260"/>
        <transition id="15b3e171-1725-4e09-b3cc-39181873044d">
            <read>
                <symbol>0</symbol>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>1</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="33d74080-61b8-4ff7-a8c4-5a88cb810d35" labelx="0" labely="-75" to="dfc99d3b-bc06-4d94-8425-e9c2b0f74991">
        <transition id="e6a96853-72b8-409d-9e7c-d8a21cf9a95c">
            <read>
                <symbol>#</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="dfc99d3b-bc06-4d94-8425-e9c2b0f74991" labelx="0" labely="80" to="dfc99d3b-bc06-4d94-8425-e9c2b0f74991">
        <transition id="d5833aa6-d019-43c3-b9bf-5d95a032b102">
            <read>
                <symbol>1</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="5d88fff9-f6e0-45ee-ae08-ab98a4288c58">
            <read>
                <symbol>0</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="dfc99d3b-bc06-4d94-8425-e9c2b0f74991" labelx="0" labely="85" to="e3ab2cdb-d1e2-4a18-a530-3d2cbe448ff9">
        <transition id="4b59c306-bbf0-4f56-9641-0eb1dd4dc232">
            <read>
                <symbol>#</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="92f333ee-0318-407b-927e-036dc48e68ec" labelx="0" labely="15" to="db734b0a-6034-4979-9e75-62e3398710d2">
        <transition id="2a0d0666-4609-4761-915f-d9fc3349922e">
            <read>
                <symbol>*</symbol>
                <symbol>1</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="92f333ee-0318-407b-927e-036dc48e68ec" labelx="0" labely="10" to="92f333ee-0318-407b-927e-036dc48e68ec">
        <via x="340" y="180"/>
        <transition id="037ca7c6-6afb-43c2-a86f-f2dc6b7fd84f">
            <read>
                <symbol>*</symbol>
                <symbol>0</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="db734b0a-6034-4979-9e75-62e3398710d2" labelx="0" labely="20" to="db734b0a-6034-4979-9e75-62e3398710d2">
        <via x="540" y="180"/>
        <transition id="3a84db2f-f00f-4d63-966a-50d6c5cd999b">
            <read>
                <symbol>*</symbol>
                <symbol>0</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="fea622f6-5777-48e1-9634-b49a09371804">
            <read>
                <symbol>*</symbol>
                <symbol>1</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="92f333ee-0318-407b-927e-036dc48e68ec" labelx="0" labely="-85" to="8f51ce8a-1183-47aa-8544-f0983e6a88dd">
        <transition id="59df2b09-d90c-46d9-9df8-4b5366db5e03">
            <read>
                <symbol>*</symbol>
                <symbol>#</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="db734b0a-6034-4979-9e75-62e3398710d2" labelx="0" labely="15" to="33d74080-61b8-4ff7-a8c4-5a88cb810d35">
        <transition id="cad968be-ad93-4fd8-aed0-8580e5f0e1fb">
            <read>
                <symbol>*</symbol>
                <symbol>#</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>L</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="95a4b6aa-975f-49c9-9dd3-366150cccb68" labelx="0" labely="-15" to="92f333ee-0318-407b-927e-036dc48e68ec">
        <transition id="ad1e190f-6a91-4748-94ac-4c2517b8903f">
            <read>
                <symbol>#</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="95a4b6aa-975f-49c9-9dd3-366150cccb68" labelx="0" labely="20" to="95a4b6aa-975f-49c9-9dd3-366150cccb68">
        <via x="140" y="180"/>
        <transition id="a9e1a00e-68fc-48dc-af2a-49a8bb93e730">
            <read>
                <symbol>0</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>0</symbol>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="42e34871-0177-4847-94a5-fc00db567668">
            <read>
                <symbol>1</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>0</symbol>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="e3ab2cdb-d1e2-4a18-a530-3d2cbe448ff9" labelx="0" labely="80" to="e3ab2cdb-d1e2-4a18-a530-3d2cbe448ff9">
        <transition id="bd0324d1-141f-4b7c-88f3-76750e34ae3b">
            <read>
                <symbol>*</symbol>
                <symbol>0</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>1</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>L</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="e3ab2cdb-d1e2-4a18-a530-3d2cbe448ff9" labelx="0" labely="85" to="962ce74a-5fe2-4e78-a32e-565df33ef7fd">
        <transition id="2c9b9430-b4b4-4b83-8268-fd1731b85309">
            <read>
                <symbol>*</symbol>
                <symbol>1</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>0</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="962ce74a-5fe2-4e78-a32e-565df33ef7fd" labelx="0" labely="80" to="962ce74a-5fe2-4e78-a32e-565df33ef7fd">
        <transition id="eca8d1fb-25e9-4882-8a96-5c0eae47a556">
            <read>
                <symbol>*</symbol>
                <symbol>1</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>L</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="1cf3c59e-a93c-49e3-9bd5-fb8915457436">
            <read>
                <symbol>*</symbol>
                <symbol>0</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>L</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="962ce74a-5fe2-4e78-a32e-565df33ef7fd" labelx="0" labely="63" to="92f333ee-0318-407b-927e-036dc48e68ec">
        <transition id="8d3fd7ea-fa6e-41e8-bb3b-d17dcdcd3688">
            <read>
                <symbol>*</symbol>
                <symbol>#</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <textbox height="60" width="230" x="330" y="10">
        <text>Very important: Add enough leading
zeros to the input word on tape 0
in order to get a correct result!</text>
    </textbox>
    <textbox height="40" width="260" x="20" y="20">
        <text>Very important: Input word on tape 0 must
be greater than the input word on tape 1!</text>
    </textbox>
    <textbox height="60" width="200" x="10" y="270">
        <text>Copy the input word from tape 0
to tape 2 and overwrite input
word on tape 0.</text>
    </textbox>
    <textbox height="40" width="110" x="270" y="110">
        <text>Check if input on
tape 1 equals 0</text>
    </textbox>
    <textbox height="30" width="150" x="780" y="70">
        <text>Addition: tape 0 + tape 2</text>
    </textbox>
    <textbox height="20" width="80" x="1020" y="210">
        <text>Carry state</text>
    </textbox>
    <textbox height="40" width="130" x="30" y="430">
        <text>Accept if number on
tape 1 equals 0</text>
    </textbox>
    <textbox height="40" width="120" x="920" y="460">
        <text>Decrement number
on tape 1</text>
    </textbox>
</machine>
