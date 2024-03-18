package com.fmr.ma.autoreasoning

import javax.crypto.Cipher
import javax.crypto.spec.{IvParameterSpec, SecretKeySpec}
import java.util.Base64

object EncryptionUtil {
  val key        = "aesEncryptionKey" // 16 characters = 128 bits
  val initVector = "encryptionIntVec" // 16 characters

  def encrypt(value: String): String = {
    val iv       = new IvParameterSpec(initVector.getBytes("UTF-8"))
    val skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES")
    val cipher   = Cipher.getInstance("AES/CBC/PKCS5PADDING")
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv)
    val encrypted = cipher.doFinal(value.getBytes)
    Base64.getEncoder.encodeToString(encrypted)
  }

  def decrypt(encrypted: String): String = {
    val iv       = new IvParameterSpec(initVector.getBytes("UTF-8"))
    val skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES")
    val cipher   = Cipher.getInstance("AES/CBC/PKCS5PADDING")
    cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv)
    val original = cipher.doFinal(Base64.getDecoder.decode(encrypted))
    new String(original)
  }
}
