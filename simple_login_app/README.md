𝐋𝐨𝐠𝐢𝐧 𝐚𝐧𝐝 𝐒𝐢𝐠𝐧𝐔𝐩 𝐏𝐚𝐠𝐞 𝐢𝐧 𝐀𝐧𝐝𝐫𝐨𝐢𝐝 𝐒𝐭𝐮𝐝𝐢𝐨 𝐮𝐬𝐢𝐧𝐠 𝐅𝐢𝐫𝐞𝐛𝐚𝐬𝐞 𝐑𝐞𝐚𝐥𝐭𝐢𝐦𝐞 𝐃𝐚𝐭𝐚𝐛𝐚𝐬𝐞

𝑾𝒉𝒂𝒕 𝒊𝒔 𝑭𝒊𝒓𝒆𝒃𝒂𝒔𝒆 𝑹𝒆𝒂𝒍𝒕𝒊𝒎𝒆 𝑫𝒂𝒕𝒂𝒃𝒂𝒔𝒆?

Firebase is a service to applications, it provides hosting, NoSQL storage, real-time databases, social authentication, notification, and other services.
In this project, we have created a login and signup page in android studio using firebase realtime database so all our data will be saved for free! When the user signs up using a username and password gets stored in the realtime database of firebase.
For login purposes, the same credentials are checked in the firebase realtime database and if it matches with user credentials then it will lead you to the home screen otherwise it will throw an error as login failed.

𝑷𝒓𝒆-𝒓𝒆𝒒𝒖𝒊𝒔𝒊𝒕𝒆 𝑪𝒐𝒍𝒐𝒓:

Please find the brand color as #8692f7 (Lavender)

Add the color in the colors.xml file as given:

    <𝗰𝗼𝗹𝗼𝗿 𝗻𝗮𝗺𝗲="𝗹𝗮𝘃𝗲𝗻𝗱𝗲𝗿">#𝟴𝟲𝟵𝟮𝗳𝟳

𝑻𝒐 𝒎𝒂𝒌𝒆 𝒕𝒉𝒆 𝒄𝒂𝒓𝒅 𝒗𝒊𝒆𝒘 𝒕𝒐 𝒈𝒍𝒂𝒔𝒔 𝒎𝒐𝒓𝒑𝒉𝒊𝒔𝒎 𝒆𝒇𝒇𝒆𝒄𝒕, 𝒂𝒅𝒋𝒖𝒔𝒕 𝒕𝒉𝒆 𝒍𝒊𝒏𝒆 𝒐𝒇 𝒄𝒐𝒅𝒆:

    <𝒂𝒏𝒅𝒓𝒐𝒊𝒅𝒙.𝒄𝒂𝒓𝒅𝒗𝒊𝒆𝒘.𝒘𝒊𝒅𝒈𝒆𝒕.𝑪𝒂𝒓𝒅𝑽𝒊𝒆𝒘
        𝒂𝒏𝒅𝒓𝒐𝒊𝒅:𝒍𝒂𝒚𝒐𝒖𝒕_𝒘𝒊𝒅𝒕𝒉="𝒎𝒂𝒕𝒄𝒉_𝒑𝒂𝒓𝒆𝒏𝒕"
        𝒂𝒏𝒅𝒓𝒐𝒊𝒅:𝒍𝒂𝒚𝒐𝒖𝒕_𝒉𝒆𝒊𝒈𝒉𝒕="𝒘𝒓𝒂𝒑_𝒄𝒐𝒏𝒕𝒆𝒏𝒕"
        𝒂𝒏𝒅𝒓𝒐𝒊𝒅:𝒍𝒂𝒚𝒐𝒖𝒕_𝒎𝒂𝒓𝒈𝒊𝒏="30𝒅𝒑"
        𝒂𝒑𝒑:𝒄𝒂𝒓𝒅𝑪𝒐𝒓𝒏𝒆𝒓𝑹𝒂𝒅𝒊𝒖𝒔="30𝒅𝒑"
        𝒂𝒑𝒑:𝒄𝒂𝒓𝒅𝑬𝒍𝒆𝒗𝒂𝒕𝒊𝒐𝒏="0𝒅𝒑" (Set elevation to 0 for glass morphism effect)
        𝒂𝒑𝒑:𝒄𝒂𝒓𝒅𝑩𝒂𝒄𝒌𝒈𝒓𝒐𝒖𝒏𝒅𝑪𝒐𝒍𝒐𝒓="#30𝑭𝑭𝑭𝑭𝑭𝑭" (𝑨𝒅𝒋𝒖𝒔𝒕 𝒃𝒂𝒄𝒌𝒈𝒓𝒐𝒖𝒏𝒅 𝒄𝒐𝒍𝒐𝒓'𝒔 𝒂𝒍𝒑𝒉𝒂 𝒇𝒐𝒓 𝒕𝒓𝒂𝒏𝒔𝒑𝒂𝒓𝒆𝒏𝒄𝒚) >

        <.. Card View Content Here ..>
        
    </𝒂𝒏𝒅𝒓𝒐𝒊𝒅𝒙.𝒄𝒂𝒓𝒅𝒗𝒊𝒆𝒘.𝒘𝒊𝒅𝒈𝒆𝒕.𝑪𝒂𝒓𝒅𝑽𝒊𝒆𝒘>

//𝑹𝒆𝒇𝒆𝒓 𝒕𝒐 𝒕𝒉𝒆 𝒗𝒂𝒍𝒊𝒅𝒂𝒕𝒊𝒐𝒏𝑻𝒆𝒙𝒕.𝒎𝒅 𝒇𝒐𝒓 𝒗𝒂𝒍𝒊𝒅𝒂𝒕𝒊𝒐𝒏 𝒍𝒐𝒈𝒊𝒄

𝐒𝐭𝐞𝐩-𝐛𝐲-𝐒𝐭𝐞𝐩 𝐈𝐦𝐩𝐥𝐞𝐦𝐞𝐧𝐭𝐚𝐭𝐢𝐨𝐧:

𝑺𝒕𝒆𝒑 1: 𝑶𝒑𝒆𝒏 𝑨𝒏𝒅𝒓𝒐𝒊𝒅 𝑺𝒕𝒖𝒅𝒊𝒐, 𝑪𝒓𝒆𝒂𝒕𝒆 𝑵𝒆𝒘 𝑷𝒓𝒐𝒋𝒆𝒄𝒕 𝒂𝒏𝒅 𝑪𝒉𝒐𝒐𝒔𝒆 𝑬𝒎𝒑𝒕𝒚 𝑨𝒄𝒕𝒊𝒗𝒊𝒕𝒚. 𝑪𝒍𝒊𝒄𝒌 𝑭𝒊𝒏𝒊𝒔𝒉.

𝑺𝒕𝒆𝒑 2: 𝑨𝒅𝒅 𝑪𝒐𝒍𝒐𝒓 𝒖𝒏𝒅𝒆𝒓 𝒇𝒐𝒍𝒅𝒆𝒓 𝒓𝒆𝒔 ->𝒗𝒂𝒍𝒖𝒆𝒔->𝒄𝒐𝒍𝒐𝒓𝒔.𝒙𝒎𝒍
  𝒀𝒐𝒖 𝒄𝒂𝒏 𝒖𝒔𝒆 𝒂𝒏𝒚 𝒄𝒐𝒍𝒐𝒓 𝒂𝒄𝒄𝒐𝒓𝒅𝒊𝒏𝒈 𝒕𝒐 𝒚𝒐𝒖𝒓 𝒂𝒑𝒑 𝒓𝒆𝒒𝒖𝒊𝒓𝒆𝒎𝒆𝒏𝒕𝒔, 𝑰 𝒉𝒂𝒗𝒆 𝒖𝒔𝒆𝒅 𝒄𝒐𝒍𝒐𝒓 #8692𝒇7.
  
𝑺𝒕𝒆𝒑 3: 𝑪𝒓𝒆𝒂𝒕𝒆 𝒍𝒂𝒗𝒆𝒏𝒅𝒆𝒓_𝒃𝒐𝒓𝒅𝒆𝒓.𝒙𝒎𝒍/𝒄𝒖𝒔𝒕𝒐𝒎_𝒆𝒅𝒊𝒕𝒕𝒆𝒙𝒕.𝒙𝒎𝒍
  𝑹𝒊𝒈𝒉𝒕-𝒄𝒍𝒊𝒄𝒌 𝒐𝒏 𝒕𝒉𝒆 𝒅𝒓𝒂𝒘𝒂𝒃𝒍𝒆 𝒇𝒐𝒍𝒅𝒆𝒓 𝒖𝒏𝒅𝒆𝒓 𝒕𝒉𝒆 𝒓𝒆𝒔 𝒇𝒐𝒍𝒅𝒆𝒓 𝒕𝒉𝒆𝒏 𝒄𝒍𝒊𝒄𝒌 𝒐𝒏 𝑵𝒆𝒘 -> 𝑫𝒓𝒂𝒘𝒂𝒃𝒍𝒆 𝑹𝒆𝒔𝒐𝒖𝒓𝒄𝒆 𝑭𝒊𝒍𝒆
  𝑻𝒉𝒆 𝒓𝒐𝒖𝒏𝒅 𝒄𝒐𝒓𝒏𝒆𝒓𝒔 𝒂𝒓𝒆 30𝒅𝒑, 𝒚𝒐𝒖 𝒄𝒂𝒏 𝒄𝒖𝒔𝒕𝒐𝒎𝒊𝒛𝒆 𝒕𝒉𝒆𝒎 𝒑𝒆𝒓 𝒚𝒐𝒖𝒓 𝒓𝒆𝒒𝒖𝒊𝒓𝒆𝒎𝒆𝒏𝒕𝒔.
  𝑨𝒅𝒅 𝑽𝒆𝒄𝒕𝒐𝒓 𝑨𝒔𝒔𝒆𝒕 𝒐𝒇 𝒍𝒐𝒄𝒌 𝒂𝒏𝒅 𝒑𝒆𝒓𝒔𝒐𝒏 𝒊𝒄𝒐𝒏 𝒂𝒔 𝒘𝒆𝒍𝒍.

𝐓𝐲𝐩𝐞 𝐭𝐡𝐞 𝐛𝐞𝐥𝐨𝐰 𝐜𝐨𝐝𝐞:

    <𝒔𝒉𝒂𝒑𝒆 𝒙𝒎𝒍𝒏𝒔:𝒂𝒏𝒅𝒓𝒐𝒊𝒅="𝒉𝒕𝒕𝒑://𝒔𝒄𝒉𝒆𝒎𝒂𝒔.𝒂𝒏𝒅𝒓𝒐𝒊𝒅.𝒄𝒐𝒎/𝒂𝒑𝒌/𝒓𝒆𝒔/𝒂𝒏𝒅𝒓𝒐𝒊𝒅"
    𝒂𝒏𝒅𝒓𝒐𝒊𝒅:𝒔𝒉𝒂𝒑𝒆="𝒓𝒆𝒄𝒕𝒂𝒏𝒈𝒍𝒆">
    <𝒔𝒕𝒓𝒐𝒌𝒆
        𝒂𝒏𝒅𝒓𝒐𝒊𝒅:𝒘𝒊𝒅𝒕𝒉="2𝒅𝒑"
        𝒂𝒏𝒅𝒓𝒐𝒊𝒅:𝒄𝒐𝒍𝒐𝒓="@𝒄𝒐𝒍𝒐𝒓/𝒑𝒖𝒓𝒑𝒍𝒆"/>
    <𝒄𝒐𝒓𝒏𝒆𝒓𝒔
     𝒂𝒏𝒅𝒓𝒐𝒊𝒅:𝒓𝒂𝒅𝒊𝒖𝒔="30𝒅𝒑"/>

𝑺𝒕𝒆𝒑 4: 𝑭𝒊𝒓𝒆𝒃𝒂𝒔𝒆 𝑹𝒆𝒂𝒍𝒕𝒊𝒎𝒆 𝑫𝒂𝒕𝒂𝒃𝒂𝒔𝒆
  𝑮𝒐 𝒕𝒐 𝑻𝒐𝒐𝒍𝒔 -> 𝑭𝒊𝒓𝒆𝒃𝒂𝒔𝒆
  𝑺𝒆𝒍𝒆𝒄𝒕 𝑹𝒆𝒂𝒍𝒕𝒊𝒎𝒆 𝑫𝒂𝒕𝒂𝒃𝒂𝒔𝒆 𝒂𝒏𝒅 𝑪𝒍𝒊𝒄𝒌 𝒐𝒏 𝒄𝒓𝒆𝒂𝒕𝒆 𝒓𝒆𝒂𝒍𝒕𝒊𝒎𝒆 𝒅𝒂𝒕𝒂𝒃𝒂𝒔𝒆
  𝑪𝒍𝒊𝒄𝒌 𝒐𝒏 𝒄𝒐𝒏𝒏𝒆𝒄𝒕 𝒚𝒐𝒖𝒓 𝒂𝒑𝒑 𝒘𝒊𝒕𝒉 𝒇𝒊𝒓𝒆𝒃𝒂𝒔𝒆 𝒕𝒉𝒆𝒏 𝒊𝒏 𝒄𝒉𝒓𝒐𝒎𝒆 𝒇𝒊𝒓𝒆𝒃𝒂𝒔𝒆 𝒘𝒆𝒃𝒔𝒊𝒕𝒆 𝒘𝒊𝒍𝒍 𝒑𝒐𝒑 𝒖𝒑.
  𝑨𝒅𝒅 𝒚𝒐𝒖𝒓 𝒑𝒓𝒐𝒋𝒆𝒄𝒕 𝒕𝒐 𝒊𝒕, 𝒕𝒉𝒆𝒏 𝒈𝒐 𝒕𝒐 𝑹𝒆𝒂𝒍𝒕𝒊𝒎𝒆 𝑫𝒂𝒕𝒂𝒃𝒂𝒔𝒆 -> 𝑪𝒓𝒆𝒂𝒕𝒆 𝑫𝒂𝒕𝒂𝒃𝒂𝒔𝒆

  𝙏𝙝𝙚 𝙗𝙖𝙘𝙠𝙜𝙧𝙤𝙪𝙣𝙙 𝙞𝙢𝙖𝙜𝙚𝙨 𝙪𝙨𝙚𝙙 𝙖𝙧𝙚 𝙜𝙞𝙫𝙚𝙣 𝙗𝙚𝙡𝙤𝙬

![bg_image2](https://github.com/SARATH0899/simple_login_app/assets/117584193/75fe184b-a8e1-4f40-b729-4e19ab8802d1)
![bg_image](https://github.com/SARATH0899/simple_login_app/assets/117584193/d7aaa345-f87f-4e10-a19c-62b248dc7cb9)
