package com.fmr.ma.autoreasoning

import scala.io.Source
import java.nio.file.{Files, Paths}
import java.nio.charset.StandardCharsets

object EncryptJsonFile extends App {
  // Utility to read a file as a string
  def readFileAsString(filePath: String): String = {
    Source.fromFile(filePath).getLines.mkString
  }

  // Utility to write a string to a file
  def writeFile(filePath: String, content: String): Unit = {
    Files.write(Paths.get(filePath), content.getBytes(StandardCharsets.UTF_8))
  }

  // Path to the original JSON file
  val originalFilePath = "data/secret/secret.json"
  // Path where the encrypted file will be saved
  val encryptedFilePath = "data/secret/secret.enc"

  // Read the original file
  val fileContent = readFileAsString(originalFilePath)

  // Encrypt the content
  val encryptedContent = EncryptionUtil.encrypt(fileContent)

  // Write the encrypted content to a new file
  writeFile(encryptedFilePath, encryptedContent)

  println("The file has been encrypted and saved successfully.")

  // Read the encrypted file
  val fileEncryptedContent = readFileAsString(encryptedFilePath)

  // Decode the content
  val decryptedContent = EncryptionUtil.decrypt(fileEncryptedContent)

  println(
    s"The file has been decrypted and saved successfully.-->$decryptedContent"
  )
}
