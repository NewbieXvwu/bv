package dev.aaa1115910.biliapi

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Paths
import java.util.Properties

internal class BiliApiTest {

    companion object {
        private val localProperties = Properties().apply {
            val path = Paths.get("../local.properties").toAbsolutePath().toString()
            load(File(path).bufferedReader())
        }
        val SESSDATA: String =
            runCatching { localProperties.getProperty("test.sessdata") }.getOrNull() ?: ""
        val BILI_JCT: String =
            runCatching { localProperties.getProperty("test.bili_jct") }.getOrNull() ?: ""
    }

    @Test
    fun `println sessdata and bili_jct`() {
        println("SESSDATA: $SESSDATA")
        println("BILI_JCT: $BILI_JCT")
    }

    @Test
    fun `get popular videos`() {
        assertDoesNotThrow {
            runBlocking {
                val response = BiliApi.getPopularVideoData()
                println(response)
            }
        }
    }

    @Test
    fun `get video info`() {
        assertDoesNotThrow {
            runBlocking {
                val response = BiliApi.getVideoInfo(av = 170001)
                println(response)
            }
        }
    }

    @Test
    fun `get video play url`() {
        assertDoesNotThrow {
            runBlocking {
                val response = BiliApi.getVideoPlayUrl(
                    av = 648092492,
                    cid = 903675075,
                    fnval = 4048,
                    qn = 127,
                    sessData = SESSDATA
                )
                println(response)
            }
        }
    }

    @Test
    fun `get pgc video play url`() {
        runBlocking {
            println(
                BiliApi.getPgcVideoPlayUrl(
                    av = 672676070,
                    cid = 331748015,
                    fnval = 4048,
                    qn = 127,
                    sessData = SESSDATA
                )
            )
        }
    }

    @Test
    fun `get video danmaku from xml`() {
        assertDoesNotThrow {
            runBlocking {
                val response = BiliApi.getDanmakuXml(cid = 903675075)
                println(response)
            }
        }
    }

    @Test
    fun `get dynamic list with type all`() {
        assertDoesNotThrow {
            runBlocking {
                val response = BiliApi.getDynamicList(
                    type = "article",
                    sessData = SESSDATA
                )
                println(response)
            }
        }
    }

    @Test
    fun `get user info from Mr_He`() {
        assertDoesNotThrow {
            runBlocking {
                val response = BiliApi.getUserInfo(
                    uid = 163637592,
                    sessData = SESSDATA
                )
                println(response)
            }
        }
    }

    @Test
    fun `get user card info from Mr_He`() {
        assertDoesNotThrow {
            runBlocking {
                val response = BiliApi.getUserCardInfo(
                    uid = 163637592,
                    photo = true,
                    sessData = SESSDATA
                )
                println(response)
            }
        }
    }

    @Test
    fun `get self user info`() {
        assertDoesNotThrow {
            runBlocking {
                val response = BiliApi.getUserSelfInfo(
                    sessData = SESSDATA
                )
                println(response)
            }
        }
    }

    @Test
    fun `get histories`() {
        assertDoesNotThrow {
            runBlocking {
                val response = BiliApi.getHistories(
                    sessData = SESSDATA
                )
                println(response)
            }
        }
    }

    @Test
    fun `get related vidoes`() {
        assertDoesNotThrow {
            runBlocking {
                val response = BiliApi.getRelatedVideos(
                    avid = 170001
                )
                println(response)
            }
        }
    }

    @Test
    fun `get favorite folder metadata from id 2333`() {
        runBlocking {
            val response = BiliApi.getFavoriteFolderInfo(
                mediaId = 2333
            )
            println(response)
        }
    }

    @Test
    fun `get all favorite folders metadata`() {
        runBlocking {
            val response = BiliApi.getAllFavoriteFoldersInfo(
                mid = 2333,
                sessData = SESSDATA
            )
            println(response)
        }
    }

    @Test
    fun `get all favorite item ids`() {
        runBlocking {
            val response = BiliApi.getFavoriteIdList(
                mediaId = 2333,
                sessData = SESSDATA
            )
            println(response)
        }
    }

    @Test
    fun `get favorite list`() {
        runBlocking {
            val response = BiliApi.getFavoriteList(
                mediaId = 2333,
                sessData = SESSDATA
            )
            println(response)
        }
    }

    @Test
    fun `send heartbeat`() {
        runBlocking {
            val response = BiliApi.sendHeartbeat(
                avid = 170001,
                cid = 280468,
                playedTime = 23,
                sessData = SESSDATA
            )
            println(response)
        }
    }

    @Test
    fun `get video more info`() {
        runBlocking {
            val response = BiliApi.getVideoMoreInfo(
                avid = 170001,
                cid = 279786,
                sessData = SESSDATA
            ).getResponseData()
            println("lastPlayTime: ${response.lastPlayTime}")
            println("lastPlayCid: ${response.lastPlayCid}")
        }
    }

    @Test
    fun `send video like`() {
        runBlocking {
            println(
                BiliApi.sendVideoLike(
                    avid = 170001,
                    like = true,
                    csrf = BILI_JCT,
                    sessData = SESSDATA
                )
            )
        }
    }

    @Test
    fun `check video is liked`() {
        runBlocking {
            println(
                BiliApi.checkVideoLiked(
                    avid = 170001,
                    sessData = SESSDATA
                )
            )
        }
    }

    @Test
    fun `send video coin`() {
        runBlocking {
            println(
                BiliApi.sendVideoCoin(
                    avid = 170001,
                    csrf = BILI_JCT,
                    sessData = SESSDATA
                )
            )
        }
    }

    @Test
    fun `check video coin`() {
        runBlocking {
            println(
                BiliApi.checkVideoSentCoin(
                    avid = 170001,
                    sessData = SESSDATA
                )
            )
        }
    }

    @Test
    fun `add video to favorite`() {
        runBlocking {
            println(
                BiliApi.setVideoToFavorite(
                    avid = 170001,
                    addMediaIds = listOf(46912037),
                    csrf = BILI_JCT,
                    sessData = SESSDATA
                )
            )
        }
    }

    @Test
    fun `delete video from favorite`() {
        runBlocking {
            println(
                BiliApi.setVideoToFavorite(
                    avid = 170001,
                    delMediaIds = listOf(46912037),
                    csrf = BILI_JCT,
                    sessData = SESSDATA
                )
            )
        }
    }

    @Test
    fun `check is video in favorite`() {
        runBlocking {
            println(
                BiliApi.checkVideoFavoured(
                    avid = 170001,
                    sessData = SESSDATA
                )
            )
        }
    }

    @Test
    fun `get season info data`() {
        runBlocking {
            println(
                BiliApi.getSeasonInfo(
                    epId = 705917
                )
            )
        }
    }
}