package com.plugins

import com.data.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.time.LocalDate
import javax.imageio.ImageIO

object Databases {
    fun init() {
        val database = connectToSQLite()

        transaction(database) {
            SchemaUtils.create(MotorHomes, Usuarios, Pedidos, Fabricas, Anuncios, Disponibilidades, Chats, Messages, ReservasDeAluguel, ComentariosAvaliacoes, Images)
        }
        insertDefaults()
    }


    suspend fun <T> dbQuery(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
}

fun connectToSQLite(): Database {
    return Database.connect("jdbc:sqlite:C:/Desenvolvimento/WebMotorHome/Database/WebMotorHomeHostNew.db", driver = "org.sqlite.JDBC")
}

private fun insertDefaults() {

    val listMotorHomes = fun(): List<MotorHome> {
        return transaction {
            MotorHomes.selectAll().map { DatabaseUtil.resultRowToMotorhome(it) }
        }
    }

    val listUsuarios = fun(): List<Usuario> {
        return transaction {
            Usuarios.selectAll().map {  DatabaseUtil.resultRowToUser(it) }
        }
    }

    val listDisponibilidade = fun(): List<Disponibilidade> {
        return transaction {
            Disponibilidades.selectAll().map {  DatabaseUtil.resultRowToDisponibilidade(it) }
        }
    }

    val listFabrica = fun(): List<Fabrica> {
        return transaction {
            Fabricas.selectAll().map {  DatabaseUtil.resultRowToFabrica(it) }
        }
    }

    val listAnuncio = fun(): List<Anuncio> {
        return transaction {
            Anuncios.selectAll().map {  DatabaseUtil.resultRowToAnuncio(it) }
        }
    }

    val motors = mutableListOf<MotorHome>()
    listMotorHomes.invoke().let {
        if (it.isEmpty()) {
            transaction {
                val inserted = MotorHomes.insert {statement ->
                    statement[modelo] = "Kombi"
                    statement[descricao] = "Volkswagen Kombi. Com cama para casal, cozinha e fogão interno"
                    statement[ano] = 1989
                    statement[exposicao] = false
                    statement[avaliacao] = null
                    statement[montadora] = "Volkswagen"
                    statement[placa] = "ICV2F95"
                }
                inserted.resultedValues?.let { result ->
                    result.singleOrNull()?.let {row ->
                        motors.add(0, DatabaseUtil.resultRowToMotorhome(row) )
                    }
                }
            }
            transaction {
                val inserted = MotorHomes.insert {statement ->
                    statement[modelo] = "Master"
                    statement[descricao] = "Renault Master. Com banheiro interno, cozinha e fogão internos. Possui direção hidraulica, ar condicionado."
                    statement[ano] = 2009
                    statement[exposicao] = false
                    statement[avaliacao] = 5
                    statement[montadora] = "Renault"
                    statement[placa] = "OCI4L53"
                }
                inserted.resultedValues?.let { result ->
                    result.singleOrNull()?.let {row ->
                        motors.add(1, DatabaseUtil.resultRowToMotorhome(row) )
                    }
                }
            }

            transaction {
                val inserted = MotorHomes.insert {statement ->
                    statement[modelo] = "Sprinter"
                    statement[descricao] = "Sprinter. Com banheiro interno, cozinha e fogão internos. Possui direção hidraulica, ar condicionado."
                    statement[ano] = 2012
                    statement[exposicao] = false
                    statement[avaliacao] = 5
                    statement[montadora] = "Renault"
                    statement[placa] = "DFI4L43"
                }
                inserted.resultedValues?.let { result ->
                    result.singleOrNull()?.let {row ->
                        motors.add(1, DatabaseUtil.resultRowToMotorhome(row) )
                    }
                }
            }
        }

    }

    listUsuarios.invoke().let {
        if (it.isEmpty()) {
            transaction {
                Usuarios.insert {statement ->
                    statement[nome] = "Dailon"
                    statement[sobrenome] = "Petersen"
                    statement[email] = "dailon.fernando@mail.com"
                    statement[password] = "senha"
                    statement[telefone] = "51995530388"
                }
                Usuarios.insert {statement ->
                    statement[nome] = "Manoela"
                    statement[sobrenome] = "Espindola"
                    statement[email] = "manoela.espindola@mail.com"
                    statement[password] = "senha"
                    statement[telefone] = "51995530388"
                }
            }
        }
    }

    val dispos = mutableListOf<Disponibilidade>()
    listDisponibilidade.invoke().let {
        if (it.isEmpty()) {
            transaction {
                var inserted = Disponibilidades.insert { statement ->
                    statement[dataInicio] = LocalDate.now()
                    statement[dataFim] = LocalDate.of(2023, 12, 3)
                }

                inserted.resultedValues?.let { result ->
                    result.singleOrNull()?.let {row ->
                        dispos.add(0, DatabaseUtil.resultRowToDisponibilidade(row) )
                    }
                }

                inserted = Disponibilidades.insert {statement ->
                    statement[dataInicio] = LocalDate.of(2023, 2, 2)
                    statement[dataFim] = LocalDate.of(2024, 12, 3)
                }

                inserted.resultedValues?.let { result ->
                    result.singleOrNull()?.let {row ->
                        dispos.add(1, DatabaseUtil.resultRowToDisponibilidade(row) )
                    }
                }
            }
        }
    }

    val firstId = 0
    val userId = 1
    listFabrica.invoke().let {
        if (it.isEmpty()) {
            transaction {
                Fabricas.insert {statement ->
                    statement[idUser] = firstId
                    statement[nomeFantasia] = "Viagens sobre rodas"
                    statement[razaoSocial] = "MANOELA ESPINDOLA ME"
                    statement[email] = "viagensobrerodas@outlook.com"
                    statement[telefone] = "51995530388"
                    statement[cnpj] = "64763273000170"
                }
                Fabricas.insert {statement ->
                    statement[idUser] = userId
                    statement[nomeFantasia] = "Motorhomes Veiculos"
                    statement[razaoSocial] = "MOTORHOMES VEICULOS LTDA"
                    statement[email] = "viagensobrerodas@outlook.com"
                    statement[telefone] = "51995530388"
                    statement[cnpj] = "01898719000154"
                }
            }
        }
    }

    listAnuncio.invoke().let {
        if (it.isEmpty()) {
            transaction {
                Anuncios.insert { statement ->
                    statement[idMotorhome] = 0
                    statement[idCriador] = 1
                    statement[precoAluguel] = 0.00
                    statement[precoVenda] =  0.00
                    statement[descricao] = motors[0].descricao
                    statement[disponivelParaAluguel] = false
                    statement[dataDeDisponibilidadeAluguel] = dispos[0].id!!
                    statement[dataDeDisponibilidadeExposicao] = dispos[1].id!!
                }
                Anuncios.insert { statement ->
                    statement[idMotorhome] = 1
                    statement[idCriador] = 1
                    statement[precoAluguel] = 0.00
                    statement[precoVenda] =  0.00
                    statement[descricao] = motors[1].descricao
                    statement[disponivelParaAluguel] = false
                    statement[dataDeDisponibilidadeAluguel] = dispos[0].id!!
                    statement[dataDeDisponibilidadeExposicao] = dispos[1].id!!
                }
                Anuncios.insert { statement ->
                    statement[idMotorhome] = 2
                    statement[idCriador] = 1
                    statement[precoAluguel] = 0.00
                    statement[precoVenda] =  0.00
                    statement[descricao] = motors[2].descricao
                    statement[disponivelParaAluguel] = false
                    statement[dataDeDisponibilidadeAluguel] = dispos[0].id!!
                    statement[dataDeDisponibilidadeExposicao] = dispos[1].id!!
                }
            }
        }
    }
}


object DatabaseUtil {
    fun convertBytesToImage(imageDataBytes: ByteArray): BufferedImage? {
        try {
            val inputStream = ByteArrayInputStream(imageDataBytes)
            return ImageIO.read(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun resultRowToEnderedo(row: ResultRow) = Endereco(
        id = row[Enderecos.id],
        idUser = row[Enderecos.idUser],
        logradouro = row[Enderecos.logradouro],
        bairro = row[Enderecos.bairro],
        numero = row[Enderecos.numero],
        cidade = row[Enderecos.cidade],
        cep = row[Enderecos.cep],
    )

    fun resultRowToImage(row: ResultRow) = Image(
        id = row[Images.id],
        idMotorhome = row[Images.idMotorhome],
        imageData = row[Images.imageData]
    )
    fun resultRowToMessage(row: ResultRow) = Message(
        id = row[Messages.id],
        idAutor = row[Messages.idAutor],
        chatId = row[Messages.chatId],
        msg = row[Messages.msg]
    )
    fun resultRowToFabrica(row: ResultRow) = Fabrica(
        id = row[Fabricas.id],
        idUser = row[Fabricas.idUser],
        nomeFantasia = row[Fabricas.nomeFantasia],
        razaoSocial = row[Fabricas.razaoSocial],
        email = row[Fabricas.email],
        telefone = row[Fabricas.telefone],
        cnpj = row[Fabricas.cnpj]
    )
    fun resultRowToDisponibilidade(row: ResultRow) = Disponibilidade(
        id = row[Disponibilidades.id],
        dataInicio = row[Disponibilidades.dataInicio],
        dataFim = row[Disponibilidades.dataFim]
    )
    fun resultRowToChat(row: ResultRow) = Chat(
        idAnunciante = row[Chats.idAnunciante],
        idCliente = row[Chats.idCliente],
        idAnuncio = row[Chats.idAnuncio],
        nomeAnuncio = row[Chats.nomeAnuncio],
        lastMessage = row[Chats.lastMessage]
    )
    fun resultRowToAnuncio(row: ResultRow) = Anuncio(
        id = row[Anuncios.id],
        idMotorhome = row[Anuncios.idMotorhome],
        idCriador = row[Anuncios.idCriador],
        precoAluguel = row[Anuncios.precoAluguel],
        precoVenda = row[Anuncios.precoVenda],
        disponivelParaAluguel = row[Anuncios.disponivelParaAluguel],
        descricao = row[Anuncios.descricao],
        dataDeDisponibilidadeAluguel = row[Anuncios.dataDeDisponibilidadeAluguel],
        dataDeDisponibilidadeExposicao = row[Anuncios.dataDeDisponibilidadeExposicao]
    )
    fun resultRowToUser(row: ResultRow) = Usuario(
        id = row[Usuarios.id],
        nome = row[Usuarios.nome],
        sobrenome = row[Usuarios.sobrenome],
        email = row[Usuarios.email],
        password = row[Usuarios.password],
        telefone = row[Usuarios.telefone]
    )

    fun resultRowToMotorhome(row: ResultRow) = MotorHome(
        id = row[MotorHomes.id],
        modelo = row[MotorHomes.modelo],
        descricao = row[MotorHomes.descricao],
        ano = row[MotorHomes.ano],
        exposicao = row[MotorHomes.exposicao],
        montadora = row[MotorHomes.montadora],
        placa = row[MotorHomes.placa],
        avaliacao = row[MotorHomes.avaliacao]
    )

    fun resultRowToOrder(row: ResultRow) = Pedido(
        id = row[Pedidos.id],
        idFabrica = row[Pedidos.idFabrica],
        idCliente = row[Pedidos.idCliente],
        descricao = row[Pedidos.descricao],
        preco = row[Pedidos.preco].toDouble()
    )
}

