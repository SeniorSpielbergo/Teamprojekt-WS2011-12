<?xml version="1.0" encoding="utf-8" standalone="no"?>
<machine name="0^n 1^n 2^n" tapes="1" xml-version="3">
    <tape type="graphic">
        <name>Action</name>
        <input>
            <symbol>0</symbol>
            <symbol>0</symbol>
            <symbol>1</symbol>
            <symbol>1</symbol>
            <symbol>2</symbol>
            <symbol>2</symbol>
        </input>
    </tape>
    <state final="no" height="50" id="c0534004-d95e-4d1f-8ecb-f83c1d8bec94" start="yes" width="50" x="500" y="330">
        <name>q_0</name>
    </state>
    <state final="yes" height="50" id="06990be0-bf90-4205-b8c2-6381cf03d2e5" start="no" width="50" x="500" y="470">
        <name>q_F</name>
    </state>
    <state final="no" height="50" id="4db7ad0d-81e0-4e1c-82ce-2b134aa38aed" start="no" width="50" x="600" y="150">
        <name>r_5</name>
    </state>
    <state final="no" height="50" id="09d7fc8c-fff3-4977-9c87-72f968ad6ef0" start="no" width="50" x="420" y="150">
        <name>l_5</name>
    </state>
    <state final="no" height="50" id="63081164-8258-4f45-96a1-0dd77ca3fe64" start="no" width="50" x="690" y="510">
        <name>r_7</name>
    </state>
    <state final="no" height="50" id="1329eca1-915a-474b-8b95-5c97ebf282fe" start="no" width="50" x="360" y="500">
        <name>l_7</name>
    </state>
    <state final="no" height="50" id="b61fd117-129e-477b-988b-22daf22f46ac" start="no" width="50" x="710" y="300">
        <name>r_0</name>
    </state>
    <state final="no" height="50" id="7879bbd8-83d5-41b0-82cc-0579946eca42" start="no" width="50" x="330" y="300">
        <name>l_0</name>
    </state>
    <state final="no" height="50" id="c74f58d8-b5db-41fd-8175-90a195c4fc4b" start="no" width="50" x="200" y="400">
        <name>l_6</name>
    </state>
    <state final="no" height="50" id="0d28d182-84c0-4652-b37c-575587474cfe" start="no" width="50" x="800" y="400">
        <name>r_6</name>
    </state>
    <state final="no" height="50" id="abc98968-55ca-4f81-b74d-6accbe9bf977" start="no" width="50" x="870" y="250">
        <name>r_1</name>
    </state>
    <state final="no" height="50" id="154c2245-e2a3-4f7a-849e-ea5312500c07" start="no" width="50" x="190" y="280">
        <name>l_1</name>
    </state>
    <state final="no" height="50" id="108174f1-2c79-4c14-9814-692d093c3b75" start="no" width="50" x="50" y="180">
        <name>l_2</name>
    </state>
    <state final="no" height="50" id="a9f8d85d-c5dd-41d6-bdef-a02794aa570c" start="no" width="50" x="960" y="160">
        <name>r_2</name>
    </state>
    <state final="no" height="50" id="1c7777f5-b289-424f-915d-135c20e76c6d" start="no" width="50" x="170" y="60">
        <name>l_3</name>
    </state>
    <state final="no" height="50" id="95c0198f-e450-421e-8de6-6f795478dfad" start="no" width="50" x="310" y="50">
        <name>l_4</name>
    </state>
    <state final="no" height="50" id="abcc9381-8b87-4d53-b3fc-646dd3bd9a2d" start="no" width="50" x="700" y="50">
        <name>r_4</name>
    </state>
    <state final="no" height="50" id="8c67cd35-9d38-43a5-9541-57fcaaa27f3f" start="no" width="50" x="860" y="50">
        <name>r_3</name>
    </state>
    <edge from="c0534004-d95e-4d1f-8ecb-f83c1d8bec94" labelx="0" labely="65" to="06990be0-bf90-4205-b8c2-6381cf03d2e5">
        <via x="10" y="10"/>
        <transition id="b505bfcc-e69f-44c2-8848-1c6aab63ac1a">
            <read>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="4db7ad0d-81e0-4e1c-82ce-2b134aa38aed" labelx="-1" labely="54" to="c0534004-d95e-4d1f-8ecb-f83c1d8bec94">
        <transition id="be836592-6abe-44c6-82fb-fd39c0706a04">
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
    <edge from="63081164-8258-4f45-96a1-0dd77ca3fe64" labelx="0" labely="-25" to="06990be0-bf90-4205-b8c2-6381cf03d2e5">
        <transition id="095b23be-aff8-4627-9a3f-6c4fbb730501">
            <read>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="1329eca1-915a-474b-8b95-5c97ebf282fe" labelx="0" labely="19" to="06990be0-bf90-4205-b8c2-6381cf03d2e5">
        <transition id="7b24566b-d1dd-48dd-a414-12474e22ea4b">
            <read>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="c0534004-d95e-4d1f-8ecb-f83c1d8bec94" labelx="0" labely="-15" to="7879bbd8-83d5-41b0-82cc-0579946eca42">
        <transition id="4f5b5eb8-52bd-4c5e-ac44-c63b9e2bf021">
            <read>
                <symbol>2</symbol>
            </read>
            <write>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="c0534004-d95e-4d1f-8ecb-f83c1d8bec94" labelx="0" labely="15" to="b61fd117-129e-477b-988b-22daf22f46ac">
        <transition id="eeac2f40-722e-4dc1-a8fa-0b1bf43e6cde">
            <read>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="7879bbd8-83d5-41b0-82cc-0579946eca42" labelx="-1" labely="49" to="c74f58d8-b5db-41fd-8175-90a195c4fc4b">
        <transition id="57deed82-25c0-4056-933b-ca50fb6403e4">
            <read>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="c74f58d8-b5db-41fd-8175-90a195c4fc4b" labelx="-1" labely="-40" to="1329eca1-915a-474b-8b95-5c97ebf282fe">
        <transition id="5ff83040-cdec-43d3-a2d5-190243ed79fb">
            <read>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="b61fd117-129e-477b-988b-22daf22f46ac" labelx="1" labely="42" to="0d28d182-84c0-4652-b37c-575587474cfe">
        <transition id="8981253f-808d-4d35-a2d7-57bbb53f71b7">
            <read>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="0d28d182-84c0-4652-b37c-575587474cfe" labelx="-1" labely="37" to="63081164-8258-4f45-96a1-0dd77ca3fe64">
        <transition id="5d4a0a42-52a1-4e5e-8019-731b0fea5e1c">
            <read>
                <symbol>2</symbol>
            </read>
            <write>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="b61fd117-129e-477b-988b-22daf22f46ac" labelx="-1" labely="22" to="abc98968-55ca-4f81-b74d-6accbe9bf977">
        <transition id="99038dc7-3e86-4e7d-ad71-c5a9e752a282">
            <read>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="7879bbd8-83d5-41b0-82cc-0579946eca42" labelx="0" labely="25" to="154c2245-e2a3-4f7a-849e-ea5312500c07">
        <transition id="7f176741-aac3-4620-aa16-dfd4bfff7ab0">
            <read>
                <symbol>2</symbol>
            </read>
            <write>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="154c2245-e2a3-4f7a-849e-ea5312500c07" labelx="1" labely="42" to="108174f1-2c79-4c14-9814-692d093c3b75">
        <transition id="bc9102fd-c65f-437c-b781-9ee0a624ac76">
            <read>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>2</symbol>
            </write>
            <action>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="abc98968-55ca-4f81-b74d-6accbe9bf977" labelx="1" labely="-49" to="a9f8d85d-c5dd-41d6-bdef-a02794aa570c">
        <transition id="a7a51d02-353b-4ee4-8687-c7a02bdddf0c">
            <read>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>0</symbol>
            </write>
            <action>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="108174f1-2c79-4c14-9814-692d093c3b75" labelx="-1" labely="35" to="1c7777f5-b289-424f-915d-135c20e76c6d">
        <transition id="0ea17e32-a676-4161-bd76-9292e98ae7ef">
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
    </edge>
    <edge from="1c7777f5-b289-424f-915d-135c20e76c6d" labelx="0" labely="-35" to="95c0198f-e450-421e-8de6-6f795478dfad">
        <transition id="71fd54dc-8d7d-4241-903f-81e99478cc74">
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
    <edge from="95c0198f-e450-421e-8de6-6f795478dfad" labelx="1" labely="49" to="09d7fc8c-fff3-4977-9c87-72f968ad6ef0">
        <transition id="2961a98a-b0a6-4df1-983f-7960fa99c087">
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
    <edge from="abcc9381-8b87-4d53-b3fc-646dd3bd9a2d" labelx="1" labely="-49" to="4db7ad0d-81e0-4e1c-82ce-2b134aa38aed">
        <transition id="4fe571ba-9c96-4157-8921-e921b415dab7">
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
    <edge from="8c67cd35-9d38-43a5-9541-57fcaaa27f3f" labelx="0" labely="25" to="abcc9381-8b87-4d53-b3fc-646dd3bd9a2d">
        <transition id="a2ba6c24-6c93-450f-9902-139cef7be400">
            <read>
                <symbol>2</symbol>
            </read>
            <write>
                <symbol>2</symbol>
            </write>
            <action>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="a9f8d85d-c5dd-41d6-bdef-a02794aa570c" labelx="1" labely="42" to="8c67cd35-9d38-43a5-9541-57fcaaa27f3f">
        <transition id="7e1971a5-4a07-4eed-ada6-289ff7d2989f">
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
    <edge from="63081164-8258-4f45-96a1-0dd77ca3fe64" labelx="0" labely="60" to="63081164-8258-4f45-96a1-0dd77ca3fe64">
        <transition id="a59eb16f-24b6-4f3d-85e6-39b5faab6013">
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
    <edge from="0d28d182-84c0-4652-b37c-575587474cfe" labelx="0" labely="60" to="0d28d182-84c0-4652-b37c-575587474cfe">
        <transition id="15ece966-1b88-4e75-9dda-b841152f2ceb">
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
    <edge from="b61fd117-129e-477b-988b-22daf22f46ac" labelx="0" labely="60" to="b61fd117-129e-477b-988b-22daf22f46ac">
        <transition id="e3d39269-bac0-44f7-bb80-5b9deab998d0">
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
    <edge from="7879bbd8-83d5-41b0-82cc-0579946eca42" labelx="0" labely="30" to="7879bbd8-83d5-41b0-82cc-0579946eca42">
        <transition id="a3668727-6e43-4667-afc5-cffab0fc31f6">
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
    <edge from="c74f58d8-b5db-41fd-8175-90a195c4fc4b" labelx="0" labely="60" to="c74f58d8-b5db-41fd-8175-90a195c4fc4b">
        <transition id="b732a1a2-a69b-4286-abab-c265b0b4da4b">
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
    <edge from="1329eca1-915a-474b-8b95-5c97ebf282fe" labelx="0" labely="60" to="1329eca1-915a-474b-8b95-5c97ebf282fe">
        <transition id="3f0e7df0-d9f6-420f-85a9-d5a83a7fd746">
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
    <edge from="108174f1-2c79-4c14-9814-692d093c3b75" labelx="0" labely="-18" to="108174f1-2c79-4c14-9814-692d093c3b75">
        <transition id="fbda049e-6f40-45bb-8d33-1f7474ce78ec">
            <read>
                <symbol>2</symbol>
            </read>
            <write>
                <symbol>2</symbol>
            </write>
            <action>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="154c2245-e2a3-4f7a-849e-ea5312500c07" labelx="0" labely="-18" to="154c2245-e2a3-4f7a-849e-ea5312500c07">
        <transition id="81bd1175-6584-4e56-8a96-4cfc2cfa66f2">
            <read>
                <symbol>2</symbol>
            </read>
            <write>
                <symbol>2</symbol>
            </write>
            <action>
                <direction>L</direction>
            </action>
        </transition>
        <transition id="94282303-57a1-4ca8-88b2-6c4105669cb0">
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
    <edge from="abc98968-55ca-4f81-b74d-6accbe9bf977" labelx="0" labely="-18" to="abc98968-55ca-4f81-b74d-6accbe9bf977">
        <transition id="b250f9ed-b856-421e-a024-1089bab816cc">
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
        <transition id="a936ecfd-9c14-46c1-9b56-547fecfa9395">
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
    <edge from="a9f8d85d-c5dd-41d6-bdef-a02794aa570c" labelx="0" labely="60" to="a9f8d85d-c5dd-41d6-bdef-a02794aa570c">
        <transition id="e61770ed-2d46-476c-9162-16babff1d524">
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
    <edge from="8c67cd35-9d38-43a5-9541-57fcaaa27f3f" labelx="0" labely="60" to="8c67cd35-9d38-43a5-9541-57fcaaa27f3f">
        <transition id="c6102905-8258-4199-b6f2-b8e385cbd73b">
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
    <edge from="abcc9381-8b87-4d53-b3fc-646dd3bd9a2d" labelx="-1" labely="23" to="abcc9381-8b87-4d53-b3fc-646dd3bd9a2d">
        <transition id="4a49b671-4827-4f32-9db6-5058d8051cb1">
            <read>
                <symbol>2</symbol>
            </read>
            <write>
                <symbol>2</symbol>
            </write>
            <action>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="4db7ad0d-81e0-4e1c-82ce-2b134aa38aed" labelx="0" labely="60" to="4db7ad0d-81e0-4e1c-82ce-2b134aa38aed">
        <transition id="f3ecc5fd-532c-440b-a012-35604b49484e">
            <read>
                <symbol>2</symbol>
            </read>
            <write>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="1c7777f5-b289-424f-915d-135c20e76c6d" labelx="-1" labely="28" to="1c7777f5-b289-424f-915d-135c20e76c6d">
        <transition id="9b1f0114-5c31-49cb-852f-cfa5b453f33d">
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
    </edge>
    <edge from="95c0198f-e450-421e-8de6-6f795478dfad" labelx="0" labely="-18" to="95c0198f-e450-421e-8de6-6f795478dfad">
        <transition id="1d365330-15a4-4432-84fa-667c0db7049e">
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
    <edge from="09d7fc8c-fff3-4977-9c87-72f968ad6ef0" labelx="0" labely="-6" to="09d7fc8c-fff3-4977-9c87-72f968ad6ef0">
        <transition id="04bf8af6-c3e0-4f2b-94f2-d8e3fd4c0d15">
            <read>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
</machine>
