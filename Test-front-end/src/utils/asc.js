/**
 * 工具类
 */
 import CryptoJS from 'crypto-js'
 var iv = CryptoJS.enc.Utf8.parse('1234567812345678');
 var cryptoObj = {
     /* 加密 */
     encryptFunc: (word, keyStr) => {
         var key = CryptoJS.enc.Utf8.parse(keyStr);
         var encryptedObj = CryptoJS.AES.encrypt(word,key,{iv:iv,mode:CryptoJS.mode.CBC,padding:CryptoJS.pad.ZeroPadding});
         return encryptedObj.toString();
     },
     /* 解密 */
     decryptFunc: (word, keyStr) => {
         var key = CryptoJS.enc.Utf8.parse(keyStr);
         return CryptoJS.AES.decrypt(word,key,{iv:iv,padding:CryptoJS.pad.ZeroPadding});
     },
 }
 export default cryptoObj;