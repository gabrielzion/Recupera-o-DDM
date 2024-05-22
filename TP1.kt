package org.example

import com.google.gson.Gson
import org.example.br.unisanta.model.Endereco
import org.example.br.unisanta.services.ConsomeApi
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    while (true) {
        println("Digite o CEP:")
        val cep = scanner.nextLine()

        if (cep.length != 8 || !cep.all { it.isDigit() }) {
            println("CEP inválido. Por favor, digite um CEP com exatamente 8 dígitos.")
            continue
        }

        val consome = ConsomeApi()
        val responseBody = consome.obterDados("https://viacep.com.br/ws/$cep/json/")

        if (responseBody.contains("erro")) {
            println("CEP não encontrado. Por favor, tente novamente.")
            continue
        }

        val gson = Gson()
        val meuEndereco = gson.fromJson(responseBody, Endereco::class.java)

        println(meuEndereco)
    }
}