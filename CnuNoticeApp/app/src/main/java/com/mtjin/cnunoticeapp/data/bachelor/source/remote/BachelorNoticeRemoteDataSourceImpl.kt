package com.mtjin.cnunoticeapp.data.bachelor.source.remote

import com.mtjin.cnunoticeapp.data.bachelor.BachelorNotice
import io.reactivex.Observable
import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class BachelorNoticeRemoteDataSourceImpl : BachelorNoticeRemoteDataSource {
    override fun requestNotice(): Single<List<BachelorNotice>> {
        return Single.fromObservable(
            Observable.create {
                val bachNoticeList: ArrayList<BachelorNotice> = ArrayList()
                val doc: Document =
                    Jsoup.connect("https://computer.cnu.ac.kr/computer/notice/bachelor.do")
                        .get() // Base Url
                val contentElements: Elements =
                    doc.select("div[class=b-title-box]").select("a") // title, link
                val idElements: Elements = doc.select("td[class=b-num-box]") // id값
                for ((i, elem) in contentElements.withIndex()) {
                    bachNoticeList.add(
                        BachelorNotice(
                            idElements[i].text(),
                            elem.text(),
                            "https://computer.cnu.ac.kr/computer/notice/bachelor.do" + elem.attr("href")
                        )
                    )
                }
                it.onNext(bachNoticeList)
                it.onComplete()
            }
        )
    }

    override fun requestMoreNotice(offset: Int): Single<List<BachelorNotice>> {
        return Single.fromObservable(
            Observable.create {
                val bachNoticeList: ArrayList<BachelorNotice> = ArrayList()
                val doc: Document =
                    Jsoup.connect("https://computer.cnu.ac.kr/computer/notice/bachelor.do?mode=list&&articleLimit=10&article.offset=$offset")
                        .get() // Base Url
                val contentElements: Elements =
                    doc.select("div[class=b-title-box]").select("a") // title, link
                val idElements: Elements = doc.select("td[class=b-num-box]") // id값
                for ((i, elem) in contentElements.withIndex()) {
                    if (idElements[i].text() != "공지") { // 공지는 매 페이지마다 있으므로 중복제거
                        bachNoticeList.add(
                            BachelorNotice(
                                idElements[i].text(),
                                elem.text(),
                                "https://computer.cnu.ac.kr/computer/notice/bachelor.do" + elem.attr(
                                    "href"
                                )
                            )
                        )
                    }
                }
                it.onNext(bachNoticeList)
                it.onComplete()
            }
        )
    }

}