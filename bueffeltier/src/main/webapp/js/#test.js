/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
!function (e, n) {
    "object" == typeof exports && "undefined" != typeof module ? module.exports = n() : "function" == typeof define && define.amd ? define(n) : e.MarketingTracker = n()
}(this, function () {
    "use strict";

    function e(e, n) {
        return e(n = {
            exports: {}
        }, n.exports), n.exports
    }

    function n(e) {
        return !!e.constructor && "function" == typeof e.constructor.isBuffer && e.constructor.isBuffer(e)
    }

    function t(e, n) {
        try {
            return decodeURIComponent(e.join(""))
        } catch (e) {
        }
        if (1 === e.length)
            return e;
        n = n || 1;
        var a = e.slice(0, n),
                i = e.slice(n);
        return Array.prototype.concat.call([], t(a), t(i))
    }

    function a(e) {
        try {
            return decodeURIComponent(e)
        } catch (l) {
            for (var n = e.match(S), a = 1; a < n.length; a++)
                n = (e = t(n, a).join("")).match(S);
            return e
        }
    }

    function i(e, n) {
        return n.encode ? n.strict ? w(e) : encodeURIComponent(e) : e
    }
    "undefined" != typeof window ? window : "undefined" != typeof global ? global : "undefined" != typeof self && self;
    var r, s = e(function (e) {
        var n, t;
        n = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", t = {
            rotl: function (e, n) {
                return e << n | e >>> 32 - n
            },
            rotr: function (e, n) {
                return e << 32 - n | e >>> n
            },
            endian: function (e) {
                if (e.constructor == Number)
                    return 16711935 & t.rotl(e, 8) | 4278255360 & t.rotl(e, 24);
                for (var n = 0; n < e.length; n++)
                    e[n] = t.endian(e[n]);
                return e
            },
            randomBytes: function (e) {
                for (var n = []; e > 0; e--)
                    n.push(Math.floor(256 * Math.random()));
                return n
            },
            bytesToWords: function (e) {
                for (var n = [], t = 0, a = 0; t < e.length; t++, a += 8)
                    n[a >>> 5] |= e[t] << 24 - a % 32;
                return n
            },
            wordsToBytes: function (e) {
                for (var n = [], t = 0; t < 32 * e.length; t += 8)
                    n.push(e[t >>> 5] >>> 24 - t % 32 & 255);
                return n
            },
            bytesToHex: function (e) {
                for (var n = [], t = 0; t < e.length; t++)
                    n.push((e[t] >>> 4).toString(16)), n.push((15 & e[t]).toString(16));
                return n.join("")
            },
            hexToBytes: function (e) {
                for (var n = [], t = 0; t < e.length; t += 2)
                    n.push(parseInt(e.substr(t, 2), 16));
                return n
            },
            bytesToBase64: function (e) {
                for (var t = [], a = 0; a < e.length; a += 3)
                    for (var i = e[a] << 16 | e[a + 1] << 8 | e[a + 2], r = 0; r < 4; r++)
                        8 * a + 6 * r <= 8 * e.length ? t.push(n.charAt(i >>> 6 * (3 - r) & 63)) : t.push("=");
                return t.join("")
            },
            base64ToBytes: function (e) {
                e = e.replace(/[^A-Z0-9+\/]/gi, "");
                for (var t = [], a = 0, i = 0; a < e.length; i = ++a % 4)
                    0 != i && t.push((n.indexOf(e.charAt(a - 1)) & Math.pow(2, -2 * i + 8) - 1) << 2 * i | n.indexOf(e.charAt(a)) >>> 6 - 2 * i);
                return t
            }
        }, e.exports = t
    }),
            o = {
                utf8: {
                    stringToBytes: function (e) {
                        return o.bin.stringToBytes(unescape(encodeURIComponent(e)))
                    },
                    bytesToString: function (e) {
                        return decodeURIComponent(escape(o.bin.bytesToString(e)))
                    }
                },
                bin: {
                    stringToBytes: function (e) {
                        for (var n = [], t = 0; t < e.length; t++)
                            n.push(255 & e.charCodeAt(t));
                        return n
                    },
                    bytesToString: function (e) {
                        for (var n = [], t = 0; t < e.length; t++)
                            n.push(String.fromCharCode(e[t]));
                        return n.join("")
                    }
                }
            },
            l = o,
            u = function (e) {
                return null != e && (n(e) || "function" == typeof (t = e).readFloatLE && "function" == typeof t.slice && n(t.slice(0, 0)) || !!e._isBuffer);
                var t
            },
            p = e(function (e) {
                var n, t, a, i, r;
                n = s, t = l.utf8, a = u, i = l.bin, (r = function (e, s) {
                    e.constructor == String ? e = s && "binary" === s.encoding ? i.stringToBytes(e) : t.stringToBytes(e) : a(e) ? e = Array.prototype.slice.call(e, 0) : Array.isArray(e) || (e = e.toString());
                    for (var o = n.bytesToWords(e), l = 8 * e.length, u = 1732584193, p = -271733879, d = -1732584194, c = 271733878, f = 0; f < o.length; f++)
                        o[f] = 16711935 & (o[f] << 8 | o[f] >>> 24) | 4278255360 & (o[f] << 24 | o[f] >>> 8);
                    o[l >>> 5] |= 128 << l % 32, o[14 + (l + 64 >>> 9 << 4)] = l;
                    var h = r._ff,
                            g = r._gg,
                            m = r._hh,
                            b = r._ii;
                    for (f = 0; f < o.length; f += 16) {
                        var v = u,
                                k = p,
                                w = d,
                                y = c;
                        p = b(p = b(p = b(p = b(p = m(p = m(p = m(p = m(p = g(p = g(p = g(p = g(p = h(p = h(p = h(p = h(p, d = h(d, c = h(c, u = h(u, p, d, c, o[f + 0], 7, -680876936), p, d, o[f + 1], 12, -389564586), u, p, o[f + 2], 17, 606105819), c, u, o[f + 3], 22, -1044525330), d = h(d, c = h(c, u = h(u, p, d, c, o[f + 4], 7, -176418897), p, d, o[f + 5], 12, 1200080426), u, p, o[f + 6], 17, -1473231341), c, u, o[f + 7], 22, -45705983), d = h(d, c = h(c, u = h(u, p, d, c, o[f + 8], 7, 1770035416), p, d, o[f + 9], 12, -1958414417), u, p, o[f + 10], 17, -42063), c, u, o[f + 11], 22, -1990404162), d = h(d, c = h(c, u = h(u, p, d, c, o[f + 12], 7, 1804603682), p, d, o[f + 13], 12, -40341101), u, p, o[f + 14], 17, -1502002290), c, u, o[f + 15], 22, 1236535329), d = g(d, c = g(c, u = g(u, p, d, c, o[f + 1], 5, -165796510), p, d, o[f + 6], 9, -1069501632), u, p, o[f + 11], 14, 643717713), c, u, o[f + 0], 20, -373897302), d = g(d, c = g(c, u = g(u, p, d, c, o[f + 5], 5, -701558691), p, d, o[f + 10], 9, 38016083), u, p, o[f + 15], 14, -660478335), c, u, o[f + 4], 20, -405537848), d = g(d, c = g(c, u = g(u, p, d, c, o[f + 9], 5, 568446438), p, d, o[f + 14], 9, -1019803690), u, p, o[f + 3], 14, -187363961), c, u, o[f + 8], 20, 1163531501), d = g(d, c = g(c, u = g(u, p, d, c, o[f + 13], 5, -1444681467), p, d, o[f + 2], 9, -51403784), u, p, o[f + 7], 14, 1735328473), c, u, o[f + 12], 20, -1926607734), d = m(d, c = m(c, u = m(u, p, d, c, o[f + 5], 4, -378558), p, d, o[f + 8], 11, -2022574463), u, p, o[f + 11], 16, 1839030562), c, u, o[f + 14], 23, -35309556), d = m(d, c = m(c, u = m(u, p, d, c, o[f + 1], 4, -1530992060), p, d, o[f + 4], 11, 1272893353), u, p, o[f + 7], 16, -155497632), c, u, o[f + 10], 23, -1094730640), d = m(d, c = m(c, u = m(u, p, d, c, o[f + 13], 4, 681279174), p, d, o[f + 0], 11, -358537222), u, p, o[f + 3], 16, -722521979), c, u, o[f + 6], 23, 76029189), d = m(d, c = m(c, u = m(u, p, d, c, o[f + 9], 4, -640364487), p, d, o[f + 12], 11, -421815835), u, p, o[f + 15], 16, 530742520), c, u, o[f + 2], 23, -995338651), d = b(d, c = b(c, u = b(u, p, d, c, o[f + 0], 6, -198630844), p, d, o[f + 7], 10, 1126891415), u, p, o[f + 14], 15, -1416354905), c, u, o[f + 5], 21, -57434055), d = b(d, c = b(c, u = b(u, p, d, c, o[f + 12], 6, 1700485571), p, d, o[f + 3], 10, -1894986606), u, p, o[f + 10], 15, -1051523), c, u, o[f + 1], 21, -2054922799), d = b(d, c = b(c, u = b(u, p, d, c, o[f + 8], 6, 1873313359), p, d, o[f + 15], 10, -30611744), u, p, o[f + 6], 15, -1560198380), c, u, o[f + 13], 21, 1309151649), d = b(d, c = b(c, u = b(u, p, d, c, o[f + 4], 6, -145523070), p, d, o[f + 11], 10, -1120210379), u, p, o[f + 2], 15, 718787259), c, u, o[f + 9], 21, -343485551), u = u + v >>> 0, p = p + k >>> 0, d = d + w >>> 0, c = c + y >>> 0
                    }
                    return n.endian([u, p, d, c])
                })._ff = function (e, n, t, a, i, r, s) {
                    var o = e + (n & t | ~n & a) + (i >>> 0) + s;
                    return (o << r | o >>> 32 - r) + n
                }, r._gg = function (e, n, t, a, i, r, s) {
                    var o = e + (n & a | t & ~a) + (i >>> 0) + s;
                    return (o << r | o >>> 32 - r) + n
                }, r._hh = function (e, n, t, a, i, r, s) {
                    var o = e + (n ^ t ^ a) + (i >>> 0) + s;
                    return (o << r | o >>> 32 - r) + n
                }, r._ii = function (e, n, t, a, i, r, s) {
                    var o = e + (t ^ (n | ~a)) + (i >>> 0) + s;
                    return (o << r | o >>> 32 - r) + n
                }, r._blocksize = 16, r._digestsize = 16, e.exports = function (e, t) {
                    if (null == e)
                        throw new Error("Illegal argument " + e);
                    var a = n.wordsToBytes(r(e, t));
                    return t && t.asBytes ? a : t && t.asString ? i.bytesToString(a) : n.bytesToHex(a)
                }
            }),
            d = function (e) {
                var n, t = e instanceof Date ? e : new Date(e);
                return n = t, !isNaN(n.valueOf()) && n >= new Date(2015, 0, 1) ? t.toISOString().split("T")[0] : void 0
            },
            c = function (e) {
                var n = new XMLHttpRequest;
                n.open("GET", "https://api.babbel.io/gamma/v1.0.0/en/geoip/me"), n.send(null), n.onload = function () {
                    n.readyState === XMLHttpRequest.DONE && 200 === n.status && e(JSON.parse(n.responseText).geoip)
                }
            },
            f = function (e) {
                return null != decodeURIComponent(e).match(/\@/)
            },
            h = "9479907f6f98e4295184564babb5b783",
            g = function (e) {
                return e ? p([e, h].join("")) : void 0
            },
            m = function () {
                return f(window.location.href)
            },
            b = function (e) {
                return function (n) {
                    var t = n.uuid,
                            a = n.leadDate,
                            i = n.learnLanguageAlpha3;
                    if (!m()) {
                        if (!dataLayer)
                            throw new Error("DataLayer not available.");
                        dataLayer.push({
                            event: e,
                            lead_date: d(a),
                            learn_language_alpha3: i,
                            obfuscated_uuid: g(t),
                            uuid: t
                        })
                    }
                }
            },
            v = {
                load: function () {
                    if (!f(window.location.href)) {
                        window.dataLayer = window.dataLayer || [], window.dataLayer.push({
                            "gtm.start": (new Date).getTime(),
                            event: "gtm.js"
                        });
                        var e = document.getElementsByTagName("script")[0],
                                n = document.createElement("script");
                        n.async = !0, n.src = "https://www.googletagmanager.com/gtm.js?id=GTM-MDD3PT", e.parentNode.insertBefore(n, e)
                    }
                },
                init: function (e) {
                    var n = e.geoData,
                            t = void 0 === n ? {} : n,
                            a = e.locale,
                            i = e.learnLanguageAlpha3,
                            r = e.userData,
                            s = void 0 === r ? {} : r;
                    if (!m()) {
                        if (!dataLayer)
                            throw new Error("DataLayer not available.");
                        var o;
                        dataLayer.push({
                            marketing_content_group: (o = document.querySelector('meta[name="marketing_content_group"]'), o ? o.content : void 0),
                            country_alpha3: s.leadCountryAlpha3 || t.countryAlpha3,
                            geo_data: {
                                country_alpha3: t.countryAlpha3
                            },
                            lead_country_alpha3: s.leadCountryAlpha3,
                            lead_date: d(s.leadDate),
                            learn_language_alpha3: s.learnLanguageAlpha3 || i,
                            locale: a,
                            obfuscated_uuid: g(s.uuid),
                            uuid: s.uuid
                        }), t.countryAlpha3 || c(function (e) {
                            var n = e.country_code3;
                            dataLayer.push({
                                country_alpha3: n,
                                geo_data: {
                                    country_alpha3: n
                                }
                            })
                        })
                    }
                },
                trackQuestionAnswered: function (e) {
                    var n = e.question,
                            t = e.answer;
                    if (!m()) {
                        if (!dataLayer)
                            throw new Error("DataLayer not available.");
                        dataLayer.push({
                            event: "question:answered",
                            payload: {
                                question: n,
                                answer: t
                            }
                        })
                    }
                },
                trackSimpleLead: b("registration_completed"),
                trackVerifiedLead: b("email_verified"),
                trackSalesConversion: function (e, n) {
                    var t = e.id,
                            a = e.couponCode,
                            i = e.price,
                            r = e.netAmount,
                            s = e.taxAmount,
                            o = e.currency,
                            l = e.subscriptionKeyCode,
                            u = e.skuKeyCode,
                            p = e.productTitle,
                            c = n.uuid,
                            f = n.leadDate,
                            h = n.learnLanguageAlpha3;
                    if (!m()) {
                        if (!dataLayer)
                            throw new Error("DataLayer not available.");
                        dataLayer.push({
                            event: "purchase_completed",
                            lead_date: d(f),
                            learn_language_alpha3: h,
                            obfuscated_uuid: g(c),
                            uuid: c,
                            purchase: {
                                purchase_id: t,
                                coupon_code: a,
                                price: i,
                                currency: o,
                                subscription_mode_key_code: l
                            },
                            ecommerce: {
                                currencyCode: o,
                                purchase: {
                                    actionField: {
                                        id: t,
                                        revenue: i,
                                        tax: s,
                                        coupon: a
                                    },
                                    products: [{
                                            id: u,
                                            name: p,
                                            price: r,
                                            quantity: 1
                                        }]
                                }
                            }
                        })
                    }
                }
            },
            k = (r = e(function (e) {
                var n;
                n = function () {
                    return function (e) {
                        function n(a) {
                            if (t[a])
                                return t[a].exports;
                            var i = t[a] = {
                                exports: {},
                                id: a,
                                loaded: !1
                            };
                            return e[a].call(i.exports, i, i.exports, n), i.loaded = !0, i.exports
                        }
                        var t = {};
                        return n.m = e, n.c = t, n.p = "", n(0)
                    }([function (e, n, t) {
                            var a, i = (a = t(1)) && a.__esModule ? a : {
                                "default": a
                            };
                            e.exports = i["default"]
                        }, function (e, n, t) {
                            function a() {}

                            function i() {
                                var e = c.shift();
                                if (e) {
                                    var n = u.last(e);
                                    n.afterDequeue(), e.stream = function (e, n, t) {
                                        function o(e) {
                                            e = t.beforeWrite(e), f.write(e), t.afterWrite(e)
                                        }
                                        (f = new l["default"](e, t)).id = d++, f.name = t.name || f.id, r.streams[f.name] = f;
                                        var u = e.ownerDocument,
                                                p = {
                                                    close: u.close,
                                                    open: u.open,
                                                    write: u.write,
                                                    writeln: u.writeln
                                                };
                                        s(u, {
                                            close: a,
                                            open: a,
                                            write: function () {
                                                for (var e = arguments.length, n = Array(e), t = 0; t < e; t++)
                                                    n[t] = arguments[t];
                                                return o(n.join(""))
                                            },
                                            writeln: function () {
                                                for (var e = arguments.length, n = Array(e), t = 0; t < e; t++)
                                                    n[t] = arguments[t];
                                                return o(n.join("") + "\n")
                                            }
                                        });
                                        var c = f.win.onerror || a;
                                        return f.win.onerror = function (e, n, a) {
                                            t.error({
                                                msg: e + " - " + n + ": " + a
                                            }), c.apply(f.win, [e, n, a])
                                        }, f.write(n, function () {
                                            s(u, p), f.win.onerror = c, t.done(), f = null, i()
                                        }), f
                                    }.apply(void 0, e), n.afterStreamStart()
                                }
                            }

                            function r(e, n, t) {
                                if (u.isFunction(t))
                                    t = {
                                        done: t
                                    };
                                else if ("clear" === t)
                                    return c = [], f = null, void(d = 0);
                                t = u.defaults(t, p);
                                var r = [e = /^#/.test(e) ? window.document.getElementById(e.substr(1)) : e.jquery ? e[0] : e, n, t];
                                return e.postscribe = {
                                    cancel: function () {
                                        r.stream ? r.stream.abort() : r[1] = a
                                    }
                                }, t.beforeEnqueue(r), c.push(r), f || i(), e.postscribe
                            }
                            n.__esModule = !0;
                            var s = Object.assign || function (e) {
                                for (var n = 1; n < arguments.length; n++) {
                                    var t = arguments[n];
                                    for (var a in t)
                                        Object.prototype.hasOwnProperty.call(t, a) && (e[a] = t[a])
                                }
                                return e
                            };
                            n["default"] = r;
                            var o, l = (o = t(2)) && o.__esModule ? o : {
                                "default": o
                            },
                                    u = function (e) {
                                        if (e && e.__esModule)
                                            return e;
                                        var n = {};
                                        if (null != e)
                                            for (var t in e)
                                                Object.prototype.hasOwnProperty.call(e, t) && (n[t] = e[t]);
                                        return n["default"] = e, n
                                    }(t(4)),
                                    p = {
                                        afterAsync: a,
                                        afterDequeue: a,
                                        afterStreamStart: a,
                                        afterWrite: a,
                                        autoFix: !0,
                                        beforeEnqueue: a,
                                        beforeWriteToken: function (e) {
                                            return e
                                        },
                                        beforeWrite: function (e) {
                                            return e
                                        },
                                        done: a,
                                        error: function (e) {
                                            throw new Error(e.msg)
                                        },
                                        releaseAsync: !1
                                    },
                                    d = 0,
                                    c = [],
                                    f = null;
                            s(r, {
                                streams: {},
                                queue: c,
                                WriteStream: l["default"]
                            })
                        }, function (e, n, t) {
                            function a(e, n) {
                                var t = u + n,
                                        a = e.getAttribute(t);
                                return l.existy(a) ? String(a) : a
                            }

                            function i(e, n) {
                                var t = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : null,
                                        a = u + n;
                                l.existy(t) && "" !== t ? e.setAttribute(a, t) : e.removeAttribute(a)
                            }
                            n.__esModule = !0;
                            var r, s = Object.assign || function (e) {
                                for (var n = 1; n < arguments.length; n++) {
                                    var t = arguments[n];
                                    for (var a in t)
                                        Object.prototype.hasOwnProperty.call(t, a) && (e[a] = t[a])
                                }
                                return e
                            },
                                    o = (r = t(3)) && r.__esModule ? r : {
                                "default": r
                            },
                                    l = function (e) {
                                        if (e && e.__esModule)
                                            return e;
                                        var n = {};
                                        if (null != e)
                                            for (var t in e)
                                                Object.prototype.hasOwnProperty.call(e, t) && (n[t] = e[t]);
                                        return n["default"] = e, n
                                    }(t(4)),
                                    u = "data-ps-",
                                    p = function () {
                                        function e(n) {
                                            var t = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : {};
                                            !function (n) {
                                                if (!(n instanceof e))
                                                    throw new TypeError("Cannot call a class as a function")
                                            }(this), this.root = n, this.options = t, this.doc = n.ownerDocument, this.win = this.doc.defaultView || this.doc.parentWindow, this.parser = new o["default"]("", {
                                                autoFix: t.autoFix
                                            }), this.actuals = [n], this.proxyHistory = "", this.proxyRoot = this.doc.createElement(n.nodeName), this.scriptStack = [], this.writeQueue = [], i(this.proxyRoot, "proxyof", 0)
                                        }
                                        return e.prototype.write = function () {
                                            var e;
                                            for ((e = this.writeQueue).push.apply(e, arguments); !this.deferredRemote && this.writeQueue.length; ) {
                                                var n = this.writeQueue.shift();
                                                l.isFunction(n) ? this._callFunction(n) : this._writeImpl(n)
                                            }
                                        }, e.prototype._callFunction = function (e) {
                                            var n = {
                                                type: "function",
                                                value: e.name || e.toString()
                                            };
                                            this._onScriptStart(n), e.call(this.win, this.doc), this._onScriptDone(n)
                                        }, e.prototype._writeImpl = function (e) {
                                            this.parser.append(e);
                                            for (var n = void 0, t = void 0, a = void 0, i = [];
                                                    (n = this.parser.readToken()) && !(t = l.isScript(n)) && !(a = l.isStyle(n)); )
                                                (n = this.options.beforeWriteToken(n)) && i.push(n);
                                            i.length > 0 && this._writeStaticTokens(i), t && this._handleScriptToken(n), a && this._handleStyleToken(n)
                                        }, e.prototype._writeStaticTokens = function (e) {
                                            var n = this._buildChunk(e);
                                            return n.actual ? (n.html = this.proxyHistory + n.actual, this.proxyHistory += n.proxy, this.proxyRoot.innerHTML = n.html, this._walkChunk(), n) : null
                                        }, e.prototype._buildChunk = function (e) {
                                            for (var n = this.actuals.length, t = [], a = [], i = [], r = e.length, s = 0; s < r; s++) {
                                                var o = e[s],
                                                        l = o.toString();
                                                if (t.push(l), o.attrs) {
                                                    if (!/^noscript$/i.test(o.tagName)) {
                                                        var p = n++;
                                                        a.push(l.replace(/(\/?>)/, " " + u + "id=" + p + " $1")), "ps-script" !== o.attrs.id && "ps-style" !== o.attrs.id && i.push("atomicTag" === o.type ? "" : "<" + o.tagName + " " + u + "proxyof=" + p + (o.unary ? " />" : ">"))
                                                    }
                                                } else
                                                    a.push(l), i.push("endTag" === o.type ? l : "")
                                            }
                                            return {
                                                tokens: e,
                                                raw: t.join(""),
                                                actual: a.join(""),
                                                proxy: i.join("")
                                            }
                                        }, e.prototype._walkChunk = function () {
                                            for (var e = void 0, n = [this.proxyRoot]; l.existy(e = n.shift()); ) {
                                                var t = 1 === e.nodeType;
                                                if (!t || !a(e, "proxyof")) {
                                                    t && (this.actuals[a(e, "id")] = e, i(e, "id"));
                                                    var r = e.parentNode && a(e.parentNode, "proxyof");
                                                    r && this.actuals[r].appendChild(e)
                                                }
                                                n.unshift.apply(n, l.toArray(e.childNodes))
                                            }
                                        }, e.prototype._handleScriptToken = function (e) {
                                            var n = this,
                                                    t = this.parser.clear();
                                            t && this.writeQueue.unshift(t), e.src = e.attrs.src || e.attrs.SRC, (e = this.options.beforeWriteToken(e)) && (e.src && this.scriptStack.length ? this.deferredRemote = e : this._onScriptStart(e), this._writeScriptToken(e, function () {
                                                n._onScriptDone(e)
                                            }))
                                        }, e.prototype._handleStyleToken = function (e) {
                                            var n = this.parser.clear();
                                            n && this.writeQueue.unshift(n), e.type = e.attrs.type || e.attrs.TYPE || "text/css", (e = this.options.beforeWriteToken(e)) && this._writeStyleToken(e), n && this.write()
                                        }, e.prototype._writeStyleToken = function (e) {
                                            var n = this._buildStyle(e);
                                            this._insertCursor(n, "ps-style"), e.content && (n.styleSheet && !n.sheet ? n.styleSheet.cssText = e.content : n.appendChild(this.doc.createTextNode(e.content)))
                                        }, e.prototype._buildStyle = function (e) {
                                            var n = this.doc.createElement(e.tagName);
                                            return n.setAttribute("type", e.type), l.eachKey(e.attrs, function (e, t) {
                                                n.setAttribute(e, t)
                                            }), n
                                        }, e.prototype._insertCursor = function (e, n) {
                                            this._writeImpl('<span id="' + n + '"/>');
                                            var t = this.doc.getElementById(n);
                                            t && t.parentNode.replaceChild(e, t)
                                        }, e.prototype._onScriptStart = function (e) {
                                            e.outerWrites = this.writeQueue, this.writeQueue = [], this.scriptStack.unshift(e)
                                        }, e.prototype._onScriptDone = function (e) {
                                            e === this.scriptStack[0] ? (this.scriptStack.shift(), this.write.apply(this, e.outerWrites), !this.scriptStack.length && this.deferredRemote && (this._onScriptStart(this.deferredRemote), this.deferredRemote = null)) : this.options.error({
                                                msg: "Bad script nesting or script finished twice"
                                            })
                                        }, e.prototype._writeScriptToken = function (e, n) {
                                            var t = this._buildScript(e),
                                                    a = this._shouldRelease(t),
                                                    i = this.options.afterAsync;
                                            e.src && (t.src = e.src, this._scriptLoadHandler(t, a ? i : function () {
                                                n(), i()
                                            }));
                                            try {
                                                this._insertCursor(t, "ps-script"), t.src && !a || n()
                                            } catch (e) {
                                                this.options.error(e), n()
                                            }
                                        }, e.prototype._buildScript = function (e) {
                                            var n = this.doc.createElement(e.tagName);
                                            return l.eachKey(e.attrs, function (e, t) {
                                                n.setAttribute(e, t)
                                            }), e.content && (n.text = e.content), n
                                        }, e.prototype._scriptLoadHandler = function (e, n) {
                                            function t() {
                                                e = e.onload = e.onreadystatechange = e.onerror = null
                                            }

                                            function a() {
                                                t(), null != n && n(), n = null
                                            }

                                            function i(e) {
                                                t(), o(e), null != n && n(), n = null
                                            }

                                            function r(e, n) {
                                                var t = e["on" + n];
                                                null != t && (e["_on" + n] = t)
                                            }
                                            var o = this.options.error;
                                            r(e, "load"), r(e, "error"), s(e, {
                                                onload: function () {
                                                    if (e._onload)
                                                        try {
                                                            e._onload.apply(this, Array.prototype.slice.call(arguments, 0))
                                                        } catch (n) {
                                                            i({
                                                                msg: "onload handler failed " + n + " @ " + e.src
                                                            })
                                                        }
                                                    a()
                                                },
                                                onerror: function () {
                                                    if (e._onerror)
                                                        try {
                                                            e._onerror.apply(this, Array.prototype.slice.call(arguments, 0))
                                                        } catch (n) {
                                                            return void i({
                                                                msg: "onerror handler failed " + n + " @ " + e.src
                                                            })
                                                        }
                                                    i({
                                                        msg: "remote script failed " + e.src
                                                    })
                                                },
                                                onreadystatechange: function () {
                                                    /^(loaded|complete)$/.test(e.readyState) && a()
                                                }
                                            })
                                        }, e.prototype._shouldRelease = function (e) {
                                            return !/^script$/i.test(e.nodeName) || !!(this.options.releaseAsync && e.src && e.hasAttribute("async"))
                                        }, e
                                    }();
                            n["default"] = p
                        }, function (e) {
                            var n;
                            n = function () {
                                return function (e) {
                                    function n(a) {
                                        if (t[a])
                                            return t[a].exports;
                                        var i = t[a] = {
                                            exports: {},
                                            id: a,
                                            loaded: !1
                                        };
                                        return e[a].call(i.exports, i, i.exports, n), i.loaded = !0, i.exports
                                    }
                                    var t = {};
                                    return n.m = e, n.c = t, n.p = "", n(0)
                                }([function (e, n, t) {
                                        var a, i = (a = t(1)) && a.__esModule ? a : {
                                            "default": a
                                        };
                                        e.exports = i["default"]
                                    }, function (e, n, t) {
                                        function a(e) {
                                            if (e && e.__esModule)
                                                return e;
                                            var n = {};
                                            if (null != e)
                                                for (var t in e)
                                                    Object.prototype.hasOwnProperty.call(e, t) && (n[t] = e[t]);
                                            return n["default"] = e, n
                                        }
                                        n.__esModule = !0;
                                        var i, r = a(t(2)),
                                                s = a(t(3)),
                                                o = (i = t(6)) && i.__esModule ? i : {
                                            "default": i
                                        },
                                                l = t(5),
                                                u = {
                                                    comment: /^<!--/,
                                                    endTag: /^<\//,
                                                    atomicTag: /^<\s*(script|style|noscript|iframe|textarea)[\s\/>]/i,
                                                    startTag: /^</,
                                                    chars: /^[^<]/
                                                },
                                                p = function () {
                                                    function e() {
                                                        var n = this,
                                                                t = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : "",
                                                                a = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : {};
                                                        !function (n) {
                                                            if (!(n instanceof e))
                                                                throw new TypeError("Cannot call a class as a function")
                                                        }(this), this.stream = t;
                                                        var i = !1,
                                                                s = {};
                                                        for (var l in r)
                                                            r.hasOwnProperty(l) && (a.autoFix && (s[l + "Fix"] = !0), i = i || s[l + "Fix"]);
                                                        i ? (this._readToken = (0, o["default"])(this, s, function () {
                                                            return n._readTokenImpl()
                                                        }), this._peekToken = (0, o["default"])(this, s, function () {
                                                            return n._peekTokenImpl()
                                                        })) : (this._readToken = this._readTokenImpl, this._peekToken = this._peekTokenImpl)
                                                    }
                                                    return e.prototype.append = function (e) {
                                                        this.stream += e
                                                    }, e.prototype.prepend = function (e) {
                                                        this.stream = e + this.stream
                                                    }, e.prototype._readTokenImpl = function () {
                                                        var e = this._peekTokenImpl();
                                                        if (e)
                                                            return this.stream = this.stream.slice(e.length), e
                                                    }, e.prototype._peekTokenImpl = function () {
                                                        for (var e in u)
                                                            if (u.hasOwnProperty(e) && u[e].test(this.stream)) {
                                                                var n = s[e](this.stream);
                                                                if (n)
                                                                    return "startTag" === n.type && /script|style/i.test(n.tagName) ? null : (n.text = this.stream.substr(0, n.length), n)
                                                            }
                                                    }, e.prototype.peekToken = function () {
                                                        return this._peekToken()
                                                    }, e.prototype.readToken = function () {
                                                        return this._readToken()
                                                    }, e.prototype.readTokens = function (e) {
                                                        for (var n = void 0; n = this.readToken(); )
                                                            if (e[n.type] && !1 === e[n.type](n))
                                                                return
                                                    }, e.prototype.clear = function () {
                                                        var e = this.stream;
                                                        return this.stream = "", e
                                                    }, e.prototype.rest = function () {
                                                        return this.stream
                                                    }, e
                                                }();
                                        for (var d in n["default"] = p, p.tokenToString = function(e) {
                                        return e.toString()
                                        }, p.escapeAttributes = function(e) {
                                        var n = {};
                                                for (var t in e) e.hasOwnProperty(t) && (n[t] = (0, l.escapeQuotes)(e[t], null));
                                                return n
                                        }, p.supports = r, r)
                                            r.hasOwnProperty(d) && (p.browserHasFlaw = p.browserHasFlaw || !r[d] && d)
                                    }, function (e, n) {
                                        n.__esModule = !0;
                                        var t = !1,
                                                a = !1,
                                                i = window.document.createElement("div");
                                        try {
                                            var r = "<P><I></P></I>";
                                            i.innerHTML = r, n.tagSoup = t = i.innerHTML !== r
                                        } catch (e) {
                                            n.tagSoup = t = !1
                                        }
                                        try {
                                            i.innerHTML = "<P><i><P></P></i></P>", n.selfClose = a = 2 === i.childNodes.length
                                        } catch (e) {
                                            n.selfClose = a = !1
                                        }
                                        i = null, n.tagSoup = t, n.selfClose = a
                                    }, function (e, n, t) {
                                        function a(e) {
                                            var n, t, a;
                                            if (-1 !== e.indexOf(">")) {
                                                var o = e.match(s.startTag);
                                                if (o) {
                                                    var l = (n = {}, t = {}, a = o[2], o[2].replace(s.attr, function (e, i) {
                                                        arguments[2] || arguments[3] || arguments[4] || arguments[5] ? arguments[5] ? (n[arguments[5]] = "", t[arguments[5]] = !0) : n[i] = arguments[2] || arguments[3] || arguments[4] || s.fillAttr.test(i) && i || "" : n[i] = "", a = a.replace(e, "")
                                                    }), {
                                                        v: new r.StartTagToken(o[1], o[0].length, n, t, !!o[3], a.replace(/^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g, ""))
                                                    });
                                                    if ("object" === (void 0 === l ? "undefined" : i(l)))
                                                        return l.v
                                                }
                                            }
                                        }
                                        n.__esModule = !0;
                                        var i = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function (e) {
                                            return typeof e
                                        } : function (e) {
                                            return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
                                        };
                                        n.comment = function (e) {
                                            var n = e.indexOf("-->");
                                            if (n >= 0)
                                                return new r.CommentToken(e.substr(4, n - 1), n + 3)
                                        }, n.chars = function (e) {
                                            var n = e.indexOf("<");
                                            return new r.CharsToken(n >= 0 ? n : e.length)
                                        }, n.startTag = a, n.atomicTag = function (e) {
                                            var n = a(e);
                                            if (n) {
                                                var t = e.slice(n.length);
                                                if (t.match(new RegExp("</\\s*" + n.tagName + "\\s*>", "i"))) {
                                                    var i = t.match(new RegExp("([\\s\\S]*?)</\\s*" + n.tagName + "\\s*>", "i"));
                                                    if (i)
                                                        return new r.AtomicTagToken(n.tagName, i[0].length + n.length, n.attrs, n.booleanAttrs, i[1])
                                                }
                                            }
                                        }, n.endTag = function (e) {
                                            var n = e.match(s.endTag);
                                            if (n)
                                                return new r.EndTagToken(n[1], n[0].length)
                                        };
                                        var r = t(4),
                                                s = {
                                                    startTag: /^<([\-A-Za-z0-9_]+)((?:\s+[\w\-]+(?:\s*=?\s*(?:(?:"[^"]*")|(?:'[^']*')|[^>\s]+))?)*)\s*(\/?)>/,
                                                    endTag: /^<\/([\-A-Za-z0-9_]+)[^>]*>/,
                                                    attr: /(?:([\-A-Za-z0-9_]+)\s*=\s*(?:(?:"((?:\\.|[^"])*)")|(?:'((?:\\.|[^'])*)')|([^>\s]+)))|(?:([\-A-Za-z0-9_]+)(\s|$)+)/g,
                                                    fillAttr: /^(checked|compact|declare|defer|disabled|ismap|multiple|nohref|noresize|noshade|nowrap|readonly|selected)$/i
                                                }
                                    }, function (e, n, t) {
                                        function a(e, n) {
                                            if (!(e instanceof n))
                                                throw new TypeError("Cannot call a class as a function")
                                        }
                                        n.__esModule = !0, n.EndTagToken = n.AtomicTagToken = n.StartTagToken = n.TagToken = n.CharsToken = n.CommentToken = n.Token = void 0;
                                        var i = t(5);
                                        n.Token = function s(e, n) {
                                            a(this, s), this.type = e, this.length = n, this.text = ""
                                        }, n.CommentToken = function () {
                                            function e(n, t) {
                                                a(this, e), this.type = "comment", this.length = t || (n ? n.length : 0), this.text = "", this.content = n
                                            }
                                            return e.prototype.toString = function () {
                                                return "<!--" + this.content
                                            }, e
                                        }(), n.CharsToken = function () {
                                            function e(n) {
                                                a(this, e), this.type = "chars", this.length = n, this.text = ""
                                            }
                                            return e.prototype.toString = function () {
                                                return this.text
                                            }, e
                                        }();
                                        var r = n.TagToken = function () {
                                            function e(n, t, i, r, s) {
                                                a(this, e), this.type = n, this.length = i, this.text = "", this.tagName = t, this.attrs = r, this.booleanAttrs = s, this.unary = !1, this.html5Unary = !1
                                            }
                                            return e.formatTag = function (e) {
                                                var n = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : null,
                                                        t = "<" + e.tagName;
                                                for (var a in e.attrs)
                                                    if (e.attrs.hasOwnProperty(a)) {
                                                        t += " " + a;
                                                        var r = e.attrs[a];
                                                        void 0 !== e.booleanAttrs && void 0 !== e.booleanAttrs[a] || (t += '="' + (0, i.escapeQuotes)(r) + '"')
                                                    }
                                                return e.rest && (t += " " + e.rest), e.unary && !e.html5Unary ? t += "/>" : t += ">", null != n && (t += n + "</" + e.tagName + ">"), t
                                            }, e
                                        }();
                                        n.StartTagToken = function () {
                                            function e(n, t, i, r, s, o) {
                                                a(this, e), this.type = "startTag", this.length = t, this.text = "", this.tagName = n, this.attrs = i, this.booleanAttrs = r, this.html5Unary = !1, this.unary = s, this.rest = o
                                            }
                                            return e.prototype.toString = function () {
                                                return r.formatTag(this)
                                            }, e
                                        }(), n.AtomicTagToken = function () {
                                            function e(n, t, i, r, s) {
                                                a(this, e), this.type = "atomicTag", this.length = t, this.text = "", this.tagName = n, this.attrs = i, this.booleanAttrs = r, this.unary = !1, this.html5Unary = !1, this.content = s
                                            }
                                            return e.prototype.toString = function () {
                                                return r.formatTag(this, this.content)
                                            }, e
                                        }(), n.EndTagToken = function () {
                                            function e(n, t) {
                                                a(this, e), this.type = "endTag", this.length = t, this.text = "", this.tagName = n
                                            }
                                            return e.prototype.toString = function () {
                                                return "</" + this.tagName + ">"
                                            }, e
                                        }()
                                    }, function (e, n) {
                                        n.__esModule = !0, n.escapeQuotes = function (e) {
                                            var n = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : "";
                                            return e ? e.replace(/([^"]*)"/g, function (e, n) {
                                                return /\\/.test(n) ? n + '"' : n + '\\"'
                                            }) : n
                                        }
                                    }, function (e, n) {
                                        function t(e) {
                                            return e && "startTag" === e.type && (e.unary = i.test(e.tagName) || e.unary, e.html5Unary = !/\/>$/.test(e.text)), e
                                        }

                                        function a(e, n) {
                                            var t = n.pop();
                                            e.prepend("</" + t.tagName + ">")
                                        }
                                        n.__esModule = !0, n["default"] = function (e, n, i) {
                                            function s() {
                                                var n, a, r, s, o = (a = i, r = (n = e).stream, s = t(a()), n.stream = r, s);
                                                o && u[o.type] && u[o.type](o)
                                            }
                                            var o, l = ((o = []).last = function () {
                                                return this[this.length - 1]
                                            }, o.lastTagNameEq = function (e) {
                                                var n = this.last();
                                                return n && n.tagName && n.tagName.toUpperCase() === e.toUpperCase()
                                            }, o.containsTagName = function (e) {
                                                for (var n, t = 0; n = this[t]; t++)
                                                    if (n.tagName === e)
                                                        return !0;
                                                return !1
                                            }, o),
                                                    u = {
                                                        startTag: function (t) {
                                                            var i = t.tagName;
                                                            "TR" === i.toUpperCase() && l.lastTagNameEq("TABLE") ? (e.prepend("<TBODY>"), s()) : n.selfCloseFix && r.test(i) && l.containsTagName(i) ? l.lastTagNameEq(i) ? a(e, l) : (e.prepend("</" + t.tagName + ">"), s()) : t.unary || l.push(t)
                                                        },
                                                        endTag: function (t) {
                                                            l.last() ? n.tagSoupFix && !l.lastTagNameEq(t.tagName) ? a(e, l) : l.pop() : n.tagSoupFix && (i(), s())
                                                        }
                                                    };
                                            return function () {
                                                return s(), t(i())
                                            }
                                        };
                                        var i = /^(AREA|BASE|BASEFONT|BR|COL|FRAME|HR|IMG|INPUT|ISINDEX|LINK|META|PARAM|EMBED)$/i,
                                                r = /^(COLGROUP|DD|DT|LI|OPTIONS|P|TD|TFOOT|TH|THEAD|TR)$/i
                                    }])
                            }, e.exports = n()
                        }, function (e, n) {
                            function t(e) {
                                return null != e
                            }

                            function a(e, n, t) {
                                var a = void 0,
                                        i = e && e.length || 0;
                                for (a = 0; a < i; a++)
                                    n.call(t, e[a], a)
                            }

                            function i(e, n, t) {
                                for (var a in e)
                                    e.hasOwnProperty(a) && n.call(t, a, e[a])
                            }

                            function r(e, n) {
                                return !(!e || "startTag" !== e.type && "atomicTag" !== e.type || !("tagName" in e) || !~e.tagName.toLowerCase().indexOf(n))
                            }
                            n.__esModule = !0;
                            var s = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function (e) {
                                return typeof e
                            } : function (e) {
                                return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
                            };
                            n.existy = t, n.isFunction = function (e) {
                                return "function" == typeof e
                            }, n.each = a, n.eachKey = i, n.defaults = function (e, n) {
                                return e = e || {}, i(n, function (n, a) {
                                    t(e[n]) || (e[n] = a)
                                }), e
                            }, n.toArray = function (e) {
                                try {
                                    return Array.prototype.slice.call(e)
                                } catch (i) {
                                    var n = (t = [], a(e, function (e) {
                                        t.push(e)
                                    }), {
                                        v: t
                                    });
                                    if ("object" === (void 0 === n ? "undefined" : s(n)))
                                        return n.v
                                }
                                var t
                            }, n.last = function (e) {
                                return e[e.length - 1]
                            }, n.isTag = r, n.isScript = function (e) {
                                return r(e, "script")
                            }, n.isStyle = function (e) {
                                return r(e, "style")
                            }
                        }])
                }, e.exports = n()
            })) && r.__esModule ? r["default"] : r,
            w = function (e) {
                return encodeURIComponent(e).replace(/[!'()*]/g, function (e) {
                    return "%" + e.charCodeAt(0).toString(16).toUpperCase()
                })
            },
            y = Object.getOwnPropertySymbols,
            _ = Object.prototype.hasOwnProperty,
            T = Object.prototype.propertyIsEnumerable,
            C = function () {
                try {
                    if (!Object.assign)
                        return !1;
                    var e = new String("abc");
                    if (e[5] = "de", "5" === Object.getOwnPropertyNames(e)[0])
                        return !1;
                    for (var n = {}, t = 0; t < 10; t++)
                        n["_" + String.fromCharCode(t)] = t;
                    if ("0123456789" !== Object.getOwnPropertyNames(n).map(function (e) {
                        return n[e]
                    }).join(""))
                        return !1;
                    var a = {};
                    return "abcdefghijklmnopqrst".split("").forEach(function (e) {
                        a[e] = e
                    }), "abcdefghijklmnopqrst" === Object.keys(Object.assign({}, a)).join("")
                } catch (e) {
                    return !1
                }
            }() ? Object.assign : function (e) {
        for (var n, t, a = function (e) {
            if (null == e)
                throw new TypeError("Object.assign cannot be called with null or undefined");
            return Object(e)
        }(e), i = 1; i < arguments.length; i++) {
            for (var r in n = Object(arguments[i]))
                _.call(n, r) && (a[r] = n[r]);
            if (y) {
                t = y(n);
                for (var s = 0; s < t.length; s++)
                    T.call(n, t[s]) && (a[t[s]] = n[t[s]])
            }
        }
        return a
    },
            S = new RegExp("%[a-f0-9]{2}", "gi"),
            x = new RegExp("(%[a-f0-9]{2})+", "gi"),
            N = function (e) {
                if ("string" != typeof e)
                    throw new TypeError("Expected `encodedURI` to be of type `string`, got `" + typeof e + "`");
                try {
                    return e = e.replace(/\+/g, " "), decodeURIComponent(e)
                } catch (s) {
                    return function (e) {
                        for (var n = {
                            "%FE%FF": "\ufffd\ufffd",
                            "%FF%FE": "\ufffd\ufffd"
                        }, t = x.exec(e); t; ) {
                            try {
                                n[t[0]] = decodeURIComponent(t[0])
                            } catch (e) {
                                var i = a(t[0]);
                                i !== t[0] && (n[t[0]] = i)
                            }
                            t = x.exec(e)
                        }
                        n["%C2"] = "\ufffd";
                        for (var r = Object.keys(n), s = 0; s < r.length; s++) {
                            var o = r[s];
                            e = e.replace(new RegExp(o, "g"), n[o])
                        }
                        return e
                    }(e)
                }
            },
            A = function (e, n) {
                var t = function () {
                    var e;
                    switch ((n = C( {
                            arrayFormat: "none"
                        }, n)).arrayFormat) {
                        case "index":
                            return function (n, t, a) {
                                e = /\[(\d*)\]$/.exec(n), n = n.replace(/\[\d*\]$/, ""), e ? (void 0 === a[n] && (a[n] = {}), a[n][e[1]] = t) : a[n] = t
                            };
                        case "bracket":
                            return function (n, t, a) {
                                e = /(\[\])$/.exec(n), n = n.replace(/\[\]$/, ""), e ? void 0 !== a[n] ? a[n] = [].concat(a[n], t) : a[n] = [t] : a[n] = t
                            };
                        default:
                            return function (e, n, t) {
                                void 0 !== t[e] ? t[e] = [].concat(t[e], n) : t[e] = n
                            }
                    }
                }(),
                        a = Object.create(null);
                return "string" != typeof e ? a : (e = e.trim().replace(/^(\?|#|&)/, "")) ? (e.split("&").forEach(function (e) {
                    var n = e.replace(/\+/g, " ").split("="),
                            i = n.shift(),
                            r = n.length > 0 ? n.join("=") : void 0;
                    r = void 0 === r ? null : N(r), t(N(i), r, a)
                }), Object.keys(a).sort().reduce(function (e, n) {
                    var t = a[n];
                    return Boolean(t) && "object" == typeof t && !Array.isArray(t) ? e[n] = function i(e) {
                        return Array.isArray(e) ? e.sort() : "object" == typeof e ? i(Object.keys(e)).sort(function (e, n) {
                            return Number(e) - Number(n)
                        }).map(function (n) {
                            return e[n]
                        }) : e
                    }(t) : e[n] = t, e
                }, Object.create(null))) : a
            },
            P = function (e, n) {
                var t = function (e) {
                    switch (e.arrayFormat) {
                        case "index":
                            return function (n, t, a) {
                                return null === t ? [i(n, e), "[", a, "]"].join("") : [i(n, e), "[", i(a, e), "]=", i(t, e)].join("")
                            };
                        case "bracket":
                            return function (n, t) {
                                return null === t ? i(n, e) : [i(n, e), "[]=", i(t, e)].join("")
                            };
                        default:
                            return function (n, t) {
                                return null === t ? i(n, e) : [i(n, e), "=", i(t, e)].join("")
                            }
                    }
                }(n = C({
                    encode: !0,
                    strict: !0,
                    arrayFormat: "none"
                }, n));
                return e ? Object.keys(e).sort().map(function (a) {
                    var r = e[a];
                    if (void 0 === r)
                        return "";
                    if (null === r)
                        return i(a, n);
                    if (Array.isArray(r)) {
                        var s = [];
                        return r.slice().forEach(function (e) {
                            void 0 !== e && s.push(t(a, e, s.length))
                        }), s.join("&")
                    }
                    return i(a, n) + "=" + i(r, n)
                }).filter(function (e) {
                    return e.length > 0
                }).join("&") : ""
            },
            I = function () {
                var e = document.createElement("div");
                return e.id = "ads-container", document.body.appendChild(e), e
            },
            B = document.addEventListener.bind(document),
            E = document.removeEventListener.bind(document);
    return {
        gtm: v,
        CampaignsTracker: function () {
            function e(n) {
                if (function (n) {
                    if (!(n instanceof e))
                        throw new TypeError("Cannot call a class as a function")
                }(this), !n || !n.host)
                    throw new Error("Host should be set.");
                this.host = n.host.replace(/\/$/, ""), this.adsContainer = I()
            }
            return e.prototype.getConversionURL = function (e, n) {
                var t, a, i = (t = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : {}, 0 === (a = P(t)).length ? "" : "?" + a);
                return this.host + "/accounts/" + e + "/conversions/" + n + ".js" + i
            }, e.prototype.trackUserClick = function () {
                var e = this,
                        n = A(location.search),
                        t = n.bsc,
                        a = n.btp;
                t && a && B("click", function i() {
                    var n, r, s;
                    E("click", i), r = e.host + "/" + t + "/" + a + "/clicked.gif", (s = document.createElement("img")).src = r, n = s, document.body.appendChild(n)
                })
            }, e.prototype.trackSimpleLead = function (e) {
                this.trackLead("simple_lead", e)
            }, e.prototype.trackVerifiedLead = function (e) {
                this.trackLead("verified_lead", e)
            }, e.prototype.trackSalesConversion = function (e, n) {
                var t = n.purchase_id,
                        a = n.price,
                        i = n.currency;
                this.trackLead("purchase_finished", e, {
                    purchase_id: t,
                    price: a,
                    currency: i
                })
            }, e.prototype.trackLead = function (e, n, t) {
                if (!n)
                    throw new Error("UUID should be set.");
                var a = this.getConversionURL(n, e, t);
                k("#" + this.adsContainer.id, '<script src="' + a + '"></script>')
            }, e
        }()
    }
}), b3.campaignsTracker = new MarketingTracker.CampaignsTracker({
    host: b3.settings.urlFromBabbelDomain("", "go")
}), b3.campaignsTracker.trackUserClick(),
        function () {
            var e;
            null == window.I18n && (window.I18n = {}), e = {
                en: {
                    interpolations: {
                        language: {
                            Italian: {
                                da: "Danish",
                                de: "German",
                                en: "English",
                                es: "Spanish",
                                fr: "French",
                                id: "Indonesian",
                                it: "Italian",
                                nl: "Dutch",
                                no: "Norwegian",
                                pl: "Polish",
                                pt: "Portuguese",
                                ru: "Russian",
                                sv: "Swedish",
                                tr: "Turkish"
                            },
                            "Italian (Standard)": {
                                da: "Danish",
                                de: "German",
                                en: "English",
                                es: "Spanish",
                                fr: "French",
                                id: "Indonesian",
                                it: "Italian",
                                nl: "Dutch",
                                no: "Norwegian (Bokm\xe5l)",
                                pl: "Polish",
                                pt: "Portuguese",
                                ru: "Russian",
                                sv: "Swedish",
                                tr: "Turkish"
                            },
                            "italian-url": {
                                da: "danish",
                                de: "german",
                                en: "english",
                                es: "spanish",
                                fr: "french",
                                id: "indonesian",
                                it: "italian",
                                nl: "dutch",
                                no: "norwegian",
                                pl: "polish",
                                pt: "portuguese",
                                ru: "russian",
                                sv: "swedish",
                                tr: "turkish"
                            },
                            "an Italian": {
                                da: "a Danish",
                                de: "a German",
                                en: "an English",
                                es: "a Spanish",
                                fr: "a French",
                                id: "an Indonesian",
                                it: "an Italian",
                                nl: "a Dutch",
                                no: "a Norwegian",
                                pl: "a Polish",
                                pt: "a Portuguese",
                                ru: "a Russian",
                                sv: "a Swedish",
                                tr: "a Turkish"
                            },
                            "Business Italian": {
                                da: "Business Danish",
                                de: "Business German",
                                en: "Business English",
                                es: "Business Spanish",
                                fr: "Business French",
                                id: "Business Indonesian",
                                it: "Business Italian",
                                nl: "Business Dutch",
                                no: "Business Norwegian",
                                pl: "Business Polish",
                                pt: "Business Portuguese",
                                ru: "Business Russian",
                                sv: "Business Swedish",
                                tr: "Business Turkish"
                            },
                            "business-italian-url": {
                                da: "business-danish",
                                de: "business-german",
                                en: "business-english",
                                es: "business-spanish",
                                fr: "business-french",
                                id: "business-indonesian",
                                it: "business-italian",
                                nl: "business-dutch",
                                no: "business-norwegian",
                                pl: "business-polish",
                                pt: "business-portuguese",
                                ru: "business-russian",
                                sv: "business-swedish",
                                tr: "business-turkish"
                            }
                        }
                    }
                },
                es: {
                    interpolations: {
                        language: {
                            italiano: {
                                da: "dan\xe9s",
                                de: "alem\xe1n",
                                en: "ingl\xe9s",
                                es: "espa\xf1ol",
                                fr: "franc\xe9s",
                                id: "indonesio",
                                it: "italiano",
                                nl: "holand\xe9s",
                                no: "noruego",
                                pl: "polaco",
                                pt: "portugu\xe9s",
                                ru: "ruso",
                                sv: "sueco",
                                tr: "turco"
                            },
                            "italiano (standard)": {
                                da: "dan\xe9s",
                                de: "alem\xe1n",
                                en: "ingl\xe9s",
                                es: "espa\xf1ol",
                                fr: "franc\xe9s",
                                id: "indonesio",
                                it: "italiano",
                                nl: "holand\xe9s",
                                no: "noruego (bokm\xe5l)",
                                pl: "polaco",
                                pt: "portugu\xe9s",
                                ru: "ruso",
                                sv: "sueco",
                                tr: "turco"
                            },
                            "italiano-url": {
                                da: "danes",
                                de: "aleman",
                                en: "ingles",
                                es: "espanol",
                                fr: "frances",
                                id: "indonesio",
                                it: "italiano",
                                nl: "holandes",
                                no: "noruego",
                                pl: "polaco",
                                pt: "portugues",
                                ru: "ruso",
                                sv: "sueco",
                                tr: "turco"
                            },
                            italiana: {
                                da: "danesa",
                                de: "alemana",
                                en: "inglesa",
                                es: "espa\xf1ola",
                                fr: "francesa",
                                id: "indonesia",
                                it: "italiana",
                                nl: "holandesa",
                                no: "noruega",
                                pl: "polaca",
                                pt: "portuguesa",
                                ru: "rusa",
                                sv: "sueca",
                                tr: "turca"
                            },
                            "italiano para el trabajo": {
                                da: "dan\xe9s para el trabajo",
                                de: "alem\xe1n para el trabajo",
                                en: "ingl\xe9s para el trabajo",
                                es: "espa\xf1ol para el trabajo",
                                fr: "franc\xe9s para el trabajo",
                                id: "indonesio para el trabajo",
                                it: "italiano para el trabajo",
                                nl: "holand\xe9s para el trabajo",
                                no: "noruego para el trabajo",
                                pl: "polaco para el trabajo",
                                pt: "portugu\xe9s para el trabajo",
                                ru: "ruso para el trabajo",
                                sv: "sueco para el trabajo",
                                tr: "turco para el trabajo"
                            },
                            "italiano-para-el-trabajo-url": {
                                da: "danes-para-el-trabajo",
                                de: "aleman-para-el-trabajo",
                                en: "ingles-para-el-trabajo",
                                es: "espanol-para-el-trabajo",
                                fr: "frances-para-el-trabajo",
                                id: "indonesio-para-el-trabajo",
                                it: "italiano-para-el-trabajo",
                                nl: "holandes-para-el-trabajo",
                                no: "noruego-para-el-trabajo",
                                pl: "polaco-para-el-trabajo",
                                pt: "portugues-para-el-trabajo",
                                ru: "ruso-para-el-trabajo",
                                sv: "sueco-para-el-trabajo",
                                tr: "turco-para-el-trabajo"
                            }
                        }
                    }
                },
                en_dev: {
                    interpolations: {
                        language: {
                            Italian: {
                                da: "Danish",
                                de: "German",
                                en: "English",
                                es: "Spanish",
                                fr: "French",
                                id: "Indonesian",
                                it: "Italian",
                                nl: "Dutch",
                                no: "Norwegian",
                                pl: "Polish",
                                pt: "Portuguese",
                                ru: "Russian",
                                sv: "Swedish",
                                tr: "Turkish"
                            },
                            "Italian (Standard)": {
                                da: "Danish",
                                de: "German",
                                en: "English",
                                es: "Spanish",
                                fr: "French",
                                id: "Indonesian",
                                it: "Italian",
                                nl: "Dutch",
                                no: "Norwegian (Bokm\xe5l)",
                                pl: "Polish",
                                pt: "Portuguese",
                                ru: "Russian",
                                sv: "Swedish",
                                tr: "Turkish"
                            },
                            "italian-url": {
                                da: "danish",
                                de: "german",
                                en: "english",
                                es: "spanish",
                                fr: "french",
                                id: "indonesian",
                                it: "italian",
                                nl: "dutch",
                                no: "norwegian",
                                pl: "polish",
                                pt: "portuguese",
                                ru: "russian",
                                sv: "swedish",
                                tr: "turkish"
                            },
                            "an Italian": {
                                da: "a Danish",
                                de: "a German",
                                en: "an English",
                                es: "a Spanish",
                                fr: "a French",
                                id: "an Indonesian",
                                it: "an Italian",
                                nl: "a Dutch",
                                no: "a Norwegian",
                                pl: "a Polish",
                                pt: "a Portuguese",
                                ru: "a Russian",
                                sv: "a Swedish",
                                tr: "a Turkish"
                            },
                            "Business Italian": {
                                da: "Business Danish",
                                de: "Business German",
                                en: "Business English",
                                es: "Business Spanish",
                                fr: "Business French",
                                id: "Business Indonesian",
                                it: "Business Italian",
                                nl: "Business Dutch",
                                no: "Business Norwegian",
                                pl: "Business Polish",
                                pt: "Business Portuguese",
                                ru: "Business Russian",
                                sv: "Business Swedish",
                                tr: "Business Turkish"
                            },
                            "business-italian-url": {
                                da: "business-danish",
                                de: "business-german",
                                en: "business-english",
                                es: "business-spanish",
                                fr: "business-french",
                                id: "business-indonesian",
                                it: "business-italian",
                                nl: "business-dutch",
                                no: "business-norwegian",
                                pl: "business-polish",
                                pt: "business-portuguese",
                                ru: "business-russian",
                                sv: "business-swedish",
                                tr: "business-turkish"
                            }
                        }
                    }
                },
                it: {
                    interpolations: {
                        language: {
                            italiano: {
                                da: "danese",
                                de: "tedesco",
                                en: "inglese",
                                es: "spagnolo",
                                fr: "francese",
                                id: "indonesiano",
                                it: "italiano",
                                nl: "olandese",
                                no: "norvegese",
                                pl: "polacco",
                                pt: "portoghese",
                                ru: "russo",
                                sv: "svedese",
                                tr: "turco"
                            },
                            "italiano (standard)": {
                                da: "danese",
                                de: "tedesco",
                                en: "inglese",
                                es: "spagnolo",
                                fr: "francese",
                                id: "indonesiano",
                                it: "italiano",
                                nl: "olandese",
                                no: "norvegese (bokm\xe5l)",
                                pl: "polacco",
                                pt: "portoghese",
                                ru: "russo",
                                sv: "svedese",
                                tr: "turco"
                            },
                            "italiano-url": {
                                da: "danese",
                                de: "tedesco",
                                en: "inglese",
                                es: "spagnolo",
                                fr: "francese",
                                id: "indonesiano",
                                it: "italiano",
                                nl: "olandese",
                                no: "norvegese",
                                pl: "polacco",
                                pt: "portoghese",
                                ru: "russo",
                                sv: "svedese",
                                tr: "turco"
                            },
                            "d'italiano": {
                                da: "di danese",
                                de: "di tedesco",
                                en: "d'inglese",
                                es: "di spagnolo",
                                fr: "di francese",
                                id: "di indonesiano",
                                it: "d'italiano",
                                nl: "di olandese",
                                no: "di norvegese",
                                pl: "di polacco",
                                pt: "di portoghese",
                                ru: "di russo",
                                sv: "di svedese",
                                tr: "di turco"
                            },
                            "d'italiano (standard)": {
                                da: "di danese",
                                de: "di tedesco",
                                en: "d'inglese",
                                es: "di spagnolo",
                                fr: "di francese",
                                id: "di indonesiano",
                                it: "d'italiano",
                                nl: "di olandese",
                                no: "di norvegese (bokm\xe5l)",
                                pl: "di polacco",
                                pt: "di portoghese",
                                ru: "di russo",
                                sv: "di svedese",
                                tr: "di turco"
                            },
                            "l'italiano": {
                                da: "il danese",
                                de: "il tedesco",
                                en: "l'inglese",
                                es: "lo spagnolo",
                                fr: "il francese",
                                id: "l'indonesiano",
                                it: "l'italiano",
                                nl: "l'olandese",
                                no: "il norvegese",
                                pl: "il polacco",
                                pt: "il portoghese",
                                ru: "il russo",
                                sv: "lo svedese",
                                tr: "il turco"
                            },
                            "dell'italiano": {
                                da: "del danese",
                                de: "del tedesco",
                                en: "dell'inglese",
                                es: "dello spagnolo",
                                fr: "del francese",
                                id: "dell'indonesiano",
                                it: "dell'italiano",
                                nl: "dell'olandese",
                                no: "del norvegese",
                                pl: "del polacco",
                                pt: "del portoghese",
                                ru: "del russo",
                                sv: "dello svedese",
                                tr: "del turco"
                            },
                            italiani: {
                                da: "danesi",
                                de: "tedeschi",
                                en: "inglesi",
                                es: "spagnoli",
                                fr: "francesi",
                                id: "indonesiani",
                                it: "italiani",
                                nl: "olandesi",
                                no: "norvegesi",
                                pl: "polacchi",
                                pt: "portoghesi",
                                ru: "russi",
                                sv: "svedesi",
                                tr: "turchi"
                            },
                            "all'italiano": {
                                da: "al danese",
                                de: "al tedesco",
                                en: "all'inglese",
                                es: "allo spagnolo",
                                fr: "al francese",
                                id: "all'indonesiano",
                                it: "all'italiano",
                                nl: "all'olandese",
                                no: "al norvegese",
                                pl: "al polacco",
                                pt: "al portoghese",
                                ru: "al russo",
                                sv: "allo svedese",
                                tr: "al turco"
                            },
                            italiana: {
                                da: "danese",
                                de: "tedesca",
                                en: "inglese",
                                es: "spagnola",
                                fr: "francese",
                                id: "indonesiana",
                                it: "italiana",
                                nl: "olandese",
                                no: "norvegese",
                                pl: "polacca",
                                pt: "portoghese",
                                ru: "russa",
                                sv: "svedese",
                                tr: "turca"
                            },
                            "italiano per il lavoro": {
                                da: "danese per il lavoro",
                                de: "tedesco per il lavoro",
                                en: "inglese per il lavoro",
                                es: "spagnolo per il lavoro",
                                fr: "francese per il lavoro",
                                id: "indonesiano per il lavoro",
                                it: "italiano per il lavoro",
                                nl: "olandese per il lavoro",
                                no: "norvegese per il lavoro",
                                pl: "polacco per il lavoro",
                                pt: "portoghese per il lavoro",
                                ru: "russo per il lavoro",
                                sv: "svedese per il lavoro",
                                tr: "turco per il lavoro"
                            },
                            "d'italiano per il lavoro": {
                                da: "di danese per il lavoro",
                                de: "di tedesco per il lavoro",
                                en: "d'inglese per il lavoro",
                                es: "di spagnolo per il lavoro",
                                fr: "di francese per il lavoro",
                                id: "di indonesiano per il lavoro",
                                it: "d'italiano per il lavoro",
                                nl: "di olandese per il lavoro",
                                no: "di norvegese per il lavoro",
                                pl: "di polacco per il lavoro",
                                pt: "di portoghese per il lavoro",
                                ru: "di russo per il lavoro",
                                sv: "di svedese per il lavoro",
                                tr: "di turco per il lavoro"
                            },
                            "l'italiano per il lavoro": {
                                da: "il danese per il lavoro",
                                de: "il tedesco per il lavoro",
                                en: "l'inglese per il lavoro",
                                es: "lo spagnolo per il lavoro",
                                fr: "il francese per il lavoro",
                                id: "l'indonesiano per il lavoro",
                                it: "l'italiano per il lavoro",
                                nl: "l'olandese per il lavoro",
                                no: "il norvegese per il lavoro",
                                pl: "il polacco per il lavoro",
                                pt: "il portoghese per il lavoro",
                                ru: "il russo per il lavoro",
                                sv: "lo svedese per il lavoro",
                                tr: "il turco per il lavoro"
                            },
                            "dell'italiano per il lavoro": {
                                da: "del danese per il lavoro",
                                de: "del tedesco per il lavoro",
                                en: "dell'inglese per il lavoro",
                                es: "dello spagnolo per il lavoro",
                                fr: "del francese per il lavoro",
                                id: "dell'indonesiano per il lavoro",
                                it: "dell'italiano per il lavoro",
                                nl: "dell'olandese per il lavoro",
                                no: "del norvegese per il lavoro",
                                pl: "del polacco per il lavoro",
                                pt: "del portoghese per il lavoro",
                                ru: "del russo per il lavoro",
                                sv: "dello svedese per il lavoro",
                                tr: "del turco per il lavoro"
                            },
                            "all'italiano per il lavoro": {
                                da: "al danese per il lavoro",
                                de: "al tedesco per il lavoro",
                                en: "all'inglese per il lavoro",
                                es: "allo spagnolo per il lavoro",
                                fr: "al francese per il lavoro",
                                id: "all'indonesiano per il lavoro",
                                it: "all'italiano per il lavoro",
                                nl: "all'olandese per il lavoro",
                                no: "al norvegese per il lavoro",
                                pl: "al polacco per il lavoro",
                                pt: "al portoghese per il lavoro",
                                ru: "al russo per il lavoro",
                                sv: "allo svedese per il lavoro",
                                tr: "al turco per il lavoro"
                            },
                            "italiano-per-il-lavoro-url": {
                                da: "danese-per-il-lavoro",
                                de: "tedesco-per-il-lavoro",
                                en: "inglese-per-il-lavoro",
                                es: "spagnolo-per-il-lavoro",
                                fr: "francese-per-il-lavoro",
                                id: "indonesiano-per-il-lavoro",
                                it: "italiano-per-il-lavoro",
                                nl: "olandese-per-il-lavoro",
                                no: "norvegese-per-il-lavoro",
                                pl: "polacco-per-il-lavoro",
                                pt: "portoghese-per-il-lavoro",
                                ru: "russo-per-il-lavoro",
                                sv: "svedese-per-il-lavoro",
                                tr: "turco-per-il-lavoro"
                            }
                        }
                    }
                },
                sv: {
                    interpolations: {
                        language: {
                            italienska: {
                                da: "danska",
                                de: "tyska",
                                en: "engelska",
                                es: "spanska",
                                fr: "franska",
                                id: "indonesiska",
                                it: "italienska",
                                nl: "nederl\xe4ndska",
                                no: "norska",
                                pl: "polska",
                                pt: "portugisiska",
                                ru: "ryska",
                                sv: "svenska",
                                tr: "turkiska"
                            },
                            "italienska (standard)": {
                                da: "danska",
                                de: "tyska",
                                en: "engelska",
                                es: "spanska",
                                fr: "franska",
                                id: "indonesiska",
                                it: "italienska",
                                nl: "nederl\xe4ndska",
                                no: "norska (bokm\xe5l)",
                                pl: "polska",
                                pt: "portugisiska",
                                ru: "ryska",
                                sv: "svenska",
                                tr: "turkiska"
                            },
                            "italienska-url": {
                                da: "danska",
                                de: "tyska",
                                en: "engelska",
                                es: "spanska",
                                fr: "franska",
                                id: "indonesiska",
                                it: "italienska",
                                nl: "nederlandska",
                                no: "norska",
                                pl: "polska",
                                pt: "portugisiska",
                                ru: "ryska",
                                sv: "svenska",
                                tr: "turkiska"
                            },
                            italiensk: {
                                da: "dansk",
                                de: "tysk",
                                en: "engelsk",
                                es: "spansk",
                                fr: "fransk",
                                id: "indonesisk",
                                it: "italiensk",
                                nl: "nederl\xe4ndsk",
                                no: "norsk",
                                pl: "polsk",
                                pt: "portugisisk",
                                ru: "rysk",
                                sv: "svensk",
                                tr: "turkisk"
                            },
                            italienskt: {
                                da: "danskt",
                                de: "tyskt",
                                en: "engelskt",
                                es: "spanskt",
                                fr: "franskt",
                                id: "indonesiskt",
                                it: "italienskt",
                                nl: "nederl\xe4ndskt",
                                no: "norskt",
                                pl: "polskt",
                                pt: "portugisiskt",
                                ru: "ryskt",
                                sv: "svenskt",
                                tr: "turkiskt"
                            },
                            "aff\xe4rsitalienska": {
                                da: "aff\xe4rsdanska",
                                de: "aff\xe4rstyska",
                                en: "aff\xe4rsengelska",
                                es: "aff\xe4rsspanska",
                                fr: "aff\xe4rsfranska",
                                id: "aff\xe4rsindonesiska",
                                it: "aff\xe4rsitalienska",
                                nl: "aff\xe4rsnederl\xe4ndska",
                                no: "aff\xe4rsnorska",
                                pl: "aff\xe4rspolska",
                                pt: "aff\xe4rsportugisiska",
                                ru: "aff\xe4rsryska",
                                sv: "aff\xe4rssvenska",
                                tr: "aff\xe4rsturkiska"
                            },
                            "affarsitalienska-url": {
                                da: "affarsdanska",
                                de: "affarstyska",
                                en: "affarsengelska",
                                es: "affarsspanska",
                                fr: "affarsfranska",
                                id: "affarsindonesiska",
                                it: "affarsitalienska",
                                nl: "affarsnederlandska",
                                no: "affarsnorska",
                                pl: "affarspolska",
                                pt: "affarsportugisiska",
                                ru: "aff\xe4rsryska",
                                sv: "affarssvenska",
                                tr: "affarsturkiska"
                            }
                        }
                    }
                },
                de: {
                    interpolations: {
                        language: {
                            italienisch: {
                                da: "d\xe4nisch",
                                de: "deutsch",
                                en: "englisch",
                                es: "spanisch",
                                fr: "franz\xf6sisch",
                                id: "indonesisch",
                                it: "italienisch",
                                nl: "niederl\xe4ndisch",
                                no: "norwegisch",
                                pl: "polnisch",
                                pt: "portugiesisch",
                                ru: "russisch",
                                sv: "schwedisch",
                                tr: "t\xfcrkisch"
                            },
                            "Italienisch (Standard)": {
                                da: "D\xe4nisch",
                                de: "Deutsch",
                                en: "Englisch",
                                es: "Spanisch",
                                fr: "Franz\xf6sisch",
                                id: "Indonesisch",
                                it: "Italienisch",
                                nl: "Niederl\xe4ndisch",
                                no: "Norwegisch (Bokm\xe5l)",
                                pl: "Polnisch",
                                pt: "Portugiesisch",
                                ru: "Russisch",
                                sv: "Schwedisch",
                                tr: "T\xfcrkisch"
                            },
                            "italienisch-url": {
                                da: "daenisch",
                                de: "deutsch",
                                en: "englisch",
                                es: "spanisch",
                                fr: "franzoesisch",
                                id: "indonesisch",
                                it: "italienisch",
                                nl: "niederlaendisch",
                                no: "norwegisch",
                                pl: "polnisch",
                                pt: "portugiesisch",
                                ru: "russisch",
                                sv: "schwedisch",
                                tr: "tuerkisch"
                            },
                            italienischen: {
                                da: "d\xe4nischen",
                                de: "deutschen",
                                en: "englischen",
                                es: "spanischen",
                                fr: "franz\xf6sischen",
                                id: "indonesischen",
                                it: "italienischen",
                                nl: "niederl\xe4ndischen",
                                no: "norwegischen",
                                pl: "polnischen",
                                pt: "portugiesischen",
                                ru: "russischen",
                                sv: "schwedischen",
                                tr: "t\xfcrkischen"
                            },
                            italienische: {
                                da: "d\xe4nische",
                                de: "deutsche",
                                en: "englische",
                                es: "spanische",
                                fr: "franz\xf6sische",
                                id: "indonesische",
                                it: "italienische",
                                nl: "niederl\xe4ndische",
                                no: "norwegische",
                                pl: "polnische",
                                pt: "portugiesische",
                                ru: "russische",
                                sv: "schwedische",
                                tr: "t\xfcrkische"
                            },
                            italienisches: {
                                da: "d\xe4nisches",
                                de: "deutsches",
                                en: "englisches",
                                es: "spanisches",
                                fr: "franz\xf6sisches",
                                id: "indonesisches",
                                it: "italienisches",
                                nl: "niederl\xe4ndisches",
                                no: "norwegisches",
                                pl: "polnisches",
                                pt: "portugiesisches",
                                ru: "russisches",
                                sv: "schwedisches",
                                tr: "t\xfcrkisches"
                            },
                            "italienisch f\xfcr den Beruf": {
                                da: "d\xe4nisch f\xfcr den Beruf",
                                de: "deutsch f\xfcr den Beruf",
                                en: "Business English",
                                es: "spanisch f\xfcr den Beruf",
                                fr: "franz\xf6sisch f\xfcr den Beruf",
                                id: "indonesisch f\xfcr den Beruf",
                                it: "italienisch f\xfcr den Beruf",
                                nl: "niederl\xe4ndisch f\xfcr den Beruf",
                                no: "norwegisch f\xfcr den Beruf",
                                pl: "polnisch f\xfcr den Beruf",
                                pt: "portugiesisch f\xfcr den Beruf",
                                ru: "russisch f\xfcr den Beruf",
                                sv: "schwedisch f\xfcr den Beruf",
                                tr: "t\xfcrkisch f\xfcr den Beruf"
                            },
                            "italienisch-fuer-den-beruf-url": {
                                da: "daenisch-fuer-den-beruf",
                                de: "deutsch-fuer-den-beruf",
                                en: "business-english",
                                es: "spanisch-fuer-den-beruf",
                                fr: "franzoesisch-fuer-den-beruf",
                                id: "indonesisch-fuer-den-beruf",
                                it: "italienisch-fuer-den-beruf",
                                nl: "niederlaendisch-fuer-den-beruf",
                                no: "norwegisch-fuer-den-beruf",
                                pl: "polnisch-fuer-den-beruf",
                                pt: "portugiesisch-fuer-den-beruf",
                                ru: "russisch-fuer-den-beruf",
                                sv: "schwedisch-fuer-den-beruf",
                                tr: "tuerkisch-fuer-den-beruf"
                            }
                        }
                    }
                },
                pt: {
                    interpolations: {
                        language: {
                            italiano: {
                                da: "dinamarqu\xeas",
                                de: "alem\xe3o",
                                en: "ingl\xeas",
                                es: "espanhol",
                                fr: "franc\xeas",
                                id: "indon\xe9sio",
                                it: "italiano",
                                nl: "holand\xeas",
                                no: "noruegu\xeas",
                                pl: "polon\xeas",
                                pt: "portugu\xeas",
                                ru: "russo",
                                sv: "sueco",
                                tr: "turco"
                            },
                            "italiano (norma)": {
                                da: "dinamarqu\xeas",
                                de: "alem\xe3o",
                                en: "ingl\xeas",
                                es: "espanhol",
                                fr: "franc\xeas",
                                id: "indon\xe9sio",
                                it: "italiano",
                                nl: "holand\xeas",
                                no: "noruegu\xeas (Bokm\xe5l)",
                                pl: "polon\xeas",
                                pt: "portugu\xeas",
                                ru: "russo",
                                sv: "sueco",
                                tr: "turco"
                            },
                            "italiano-url": {
                                da: "dinamarques",
                                de: "alemao",
                                en: "ingles",
                                es: "espanhol",
                                fr: "frances",
                                id: "indonesio",
                                it: "italiano",
                                nl: "holandes",
                                no: "noruegues",
                                pl: "polones",
                                pt: "portugues",
                                ru: "russo",
                                sv: "sueco",
                                tr: "turco"
                            },
                            italiana: {
                                da: "dinamarqu\xeasa",
                                de: "alem\xe3",
                                en: "inglesa",
                                es: "espanhola",
                                fr: "francesa",
                                id: "indon\xe9sia",
                                it: "italiana",
                                nl: "holandesa",
                                no: "norueguesa",
                                pl: "polonesa",
                                pt: "portuguesa",
                                ru: "russa",
                                sv: "sueca",
                                tr: "turca"
                            },
                            "italiano para o trabalho": {
                                da: "dinamarqu\xeas para o trabalho",
                                de: "alem\xe3o para o trabalho",
                                en: "ingl\xeas para o trabalho",
                                es: "espanhol para o trabalho",
                                fr: "franc\xeas para o trabalho",
                                id: "indon\xe9sio para o trabalho",
                                it: "italiano para o trabalho",
                                nl: "holand\xeas para o trabalho",
                                no: "noruegues para o trabalho",
                                pl: "polon\xeas para o trabalho",
                                pt: "portugu\xeas para o trabalho",
                                ru: "russo para o trabalho",
                                sv: "sueco para o trabalho",
                                tr: "turco para o trabalho"
                            },
                            "italiano-para-o-trabalho-url": {
                                da: "dinamarques-para-o-trabalho",
                                de: "alemao-para-o-trabalho",
                                en: "ingl\xeas-para-o-trabalho",
                                es: "espanhol-para-o-trabalho",
                                fr: "frances-para-o-trabalho",
                                id: "indonesio-para-o-trabalho",
                                it: "italiano-para-o-trabalho",
                                nl: "holandes-para-o-trabalho",
                                no: "noruegues-para-o-trabalho",
                                pl: "polones-para-o-trabalho",
                                pt: "portugues-para-o-trabalho",
                                ru: "russo-para-o-trabalho",
                                sv: "sueco-para-o-trabalho",
                                tr: "turco-para-o-trabalho"
                            }
                        }
                    }
                },
                fr: {
                    interpolations: {
                        language: {
                            italien: {
                                da: "danois",
                                de: "allemand",
                                en: "anglais",
                                es: "espagnol",
                                fr: "fran\xe7ais",
                                id: "indon\xe9sien",
                                it: "italien",
                                nl: "n\xe9erlandais",
                                no: "norv\xe9gien",
                                pl: "polonais",
                                pt: "portugais",
                                ru: "russe",
                                sv: "su\xe9dois",
                                tr: "turc"
                            },
                            "italien (standard)": {
                                da: "danois",
                                de: "allemand",
                                en: "anglais",
                                es: "espagnol",
                                fr: "fran\xe7ais",
                                id: "indon\xe9sien",
                                it: "italien",
                                nl: "n\xe9erlandais",
                                no: "norv\xe9gien (bokm\xe5l)",
                                pl: "polonais",
                                pt: "portugais",
                                ru: "russe",
                                sv: "su\xe9dois",
                                tr: "turc"
                            },
                            "italien-url": {
                                da: "danois",
                                de: "allemand",
                                en: "anglais",
                                es: "espagnol",
                                fr: "francais",
                                id: "indonesien",
                                it: "italien",
                                nl: "neerlandais",
                                no: "norvegien",
                                pl: "polonais",
                                pt: "portugais",
                                ru: "russe",
                                sv: "suedois",
                                tr: "turc"
                            },
                            italienne: {
                                da: "danoise",
                                de: "allemande",
                                en: "anglaise",
                                es: "espagnole",
                                fr: "fran\xe7aise",
                                id: "indon\xe9sienne",
                                it: "italienne",
                                nl: "n\xe9erlandaise",
                                no: "norv\xe9gienne",
                                pl: "polonaise",
                                pt: "portugaise",
                                ru: "russe",
                                sv: "su\xe9doise",
                                tr: "turque"
                            },
                            "d'italien": {
                                da: "de danois",
                                de: "d'allemand",
                                en: "d'anglais",
                                es: "d'espagnol",
                                fr: "de fran\xe7ais",
                                id: "d'indon\xe9sien",
                                it: "d'italien",
                                nl: "de n\xe9erlandais",
                                no: "de norv\xe9gien",
                                pl: "de polonais",
                                pt: "de portugais",
                                ru: "de russe",
                                sv: "de su\xe9dois",
                                tr: "de turc"
                            },
                            "d'italien (standard)": {
                                da: "de danois",
                                de: "d'allemand",
                                en: "d'anglais",
                                es: "d'espagnol",
                                fr: "de fran\xe7ais",
                                id: "d'indon\xe9sien",
                                it: "d'italien",
                                nl: "de n\xe9erlandais",
                                no: "de norv\xe9gien (bokm\xe5l)",
                                pl: "de polonais",
                                pt: "de portugais",
                                ru: "de russe",
                                sv: "de su\xe9dois",
                                tr: "de turc"
                            },
                            "l'italien": {
                                da: "le danois",
                                de: "l'allemand",
                                en: "l'anglais",
                                es: "l'espagnol",
                                fr: "le fran\xe7ais",
                                id: "l'indon\xe9sien",
                                it: "l'italien",
                                nl: "le n\xe9erlandais",
                                no: "le norv\xe9gien",
                                pl: "le polonais",
                                pt: "le portugais",
                                ru: "le russe",
                                sv: "le su\xe9dois",
                                tr: "le turc"
                            },
                            "de l'italien": {
                                da: "du danois",
                                de: "de l'allemand",
                                en: "de l'anglais",
                                es: "de l'espagnol",
                                fr: "du fran\xe7ais",
                                id: "de l'indon\xe9sien",
                                it: "de l'italien",
                                nl: "du n\xe9erlandais",
                                no: "du norv\xe9gien",
                                pl: "du polonais",
                                pt: "du portugais",
                                ru: "du russe",
                                sv: "du su\xe9dois",
                                tr: "du turc"
                            },
                            "\xe0 l'italien": {
                                da: "au danois",
                                de: "\xe0 l'allemand",
                                en: "\xe0 l'anglais",
                                es: "\xe0 l'espagnol",
                                fr: "au fran\xe7ais",
                                id: "\xe0 l'indon\xe9sien",
                                it: "\xe0 l'italien",
                                nl: "au n\xe9erlandais",
                                no: "au norv\xe9gien",
                                pl: "au polonais",
                                pt: "au portugais",
                                ru: "au russe",
                                sv: "au su\xe9dois",
                                tr: "au turc"
                            },
                            "italien des affaires": {
                                da: "danois des affaires",
                                de: "allemand des affaires",
                                en: "anglais des affaires",
                                es: "espagnol des affaires",
                                fr: "fran\xe7ais des affaires",
                                id: "indon\xe9sien des affaires",
                                it: "italien des affaires",
                                nl: "n\xe9erlandais des affaires",
                                no: "norv\xe9gien des affaires",
                                pl: "polonais des affaires",
                                pt: "portugais des affaires",
                                ru: "russe des affaires",
                                sv: "su\xe9dois des affaires",
                                tr: "turc des affaires"
                            },
                            "d'italien des affaires": {
                                da: "de danois des affaires",
                                de: "d'allemand des affaires",
                                en: "d'anglais des affaires",
                                es: "d'espagnol des affaires",
                                fr: "de fran\xe7ais des affaires",
                                id: "d'indon\xe9sien des affaires",
                                it: "d'italien des affaires",
                                nl: "de n\xe9erlandais des affaires",
                                no: "de norv\xe9gien des affaires",
                                pl: "de polonais des affaires",
                                pt: "de portugais des affaires",
                                ru: "de russe des affaires",
                                sv: "de su\xe9dois des affaires",
                                tr: "de turc des affaires"
                            },
                            "l'italien des affaires": {
                                da: "le danois des affaires",
                                de: "l'allemand des affaires",
                                en: "l'anglais des affaires",
                                es: "l'espagnol des affaires",
                                fr: "le fran\xe7ais des affaires",
                                id: "l'indon\xe9sien des affaires",
                                it: "l'italien des affaires",
                                nl: "le n\xe9erlandais des affaires",
                                no: "le norv\xe9gien des affaires",
                                pl: "le polonais des affaires",
                                pt: "le portugais des affaires",
                                ru: "le russe des affaires",
                                sv: "le su\xe9dois des affaires",
                                tr: "le turc des affaires"
                            },
                            "de l'italien des affaires": {
                                da: "du danois des affaires ",
                                de: "de l'allemand des affaires",
                                en: "de l'anglais des affaires",
                                es: "de l'espagnol des affaires",
                                fr: "du fran\xe7ais des affaires",
                                id: "de l'indon\xe9sien des affaires",
                                it: "de l'italien des affaires",
                                nl: "du n\xe9erlandais des affaires",
                                no: "du norv\xe9gien des affaires",
                                pl: "du polonais des affaires",
                                pt: "du portugais des affaires",
                                ru: "du russe des affaires",
                                sv: "du su\xe9dois des affaires",
                                tr: "du turc des affaires"
                            },
                            "\xe0 l'italien des affaires": {
                                da: "au danois des affaires",
                                de: "\xe0 l'allemand des affaires",
                                en: "\xe0 l'anglais des affaires",
                                es: "\xe0 l'anglais des affaires",
                                fr: "au fran\xe7ais des affaires",
                                id: "\xe0 l'indon\xe9sien des affaires",
                                it: "\xe0 l'italien des affaires",
                                nl: "au n\xe9erlandais des affaires",
                                no: "au norv\xe9gien des affaires",
                                pl: "au polonais des affaires",
                                pt: "au portugais des affaires",
                                ru: "au russe des affaires",
                                sv: "au su\xe9dois des affaires",
                                tr: "au turc des affaires"
                            },
                            "italien-des-affaires-url": {
                                da: "danois-des-affaires",
                                de: "allemand-des-affaires",
                                en: "anglais-des-affaires",
                                es: "espagnol-des-affaires",
                                fr: "francais-des-affaires",
                                id: "indonesien-des-affaires",
                                it: "italien-des-affaires",
                                nl: "neerlandais-des-affaires",
                                no: "norvegien-des-affaires",
                                pl: "polonais-des-affaires",
                                pt: "portugais-des-affaires",
                                ru: "russe-des-affaires",
                                sv: "suedois-des-affaires",
                                tr: "turc-des-affaires"
                            }
                        }
                    }
                },
                en_GB: {
                    interpolations: {
                        language: {
                            Italian: {
                                da: "Danish",
                                de: "German",
                                en: "English",
                                es: "Spanish",
                                fr: "French",
                                id: "Indonesian",
                                it: "Italian",
                                nl: "Dutch",
                                no: "Norwegian",
                                pl: "Polish",
                                pt: "Portuguese",
                                ru: "Russian",
                                sv: "Swedish",
                                tr: "Turkish"
                            },
                            "Italian (Standard)": {
                                da: "Danish",
                                de: "German",
                                en: "English",
                                es: "Spanish",
                                fr: "French",
                                id: "Indonesian",
                                it: "Italian",
                                nl: "Dutch",
                                no: "Norwegian (Bokm\xe5l)",
                                pl: "Polish",
                                pt: "Portuguese",
                                ru: "Russian",
                                sv: "Swedish",
                                tr: "Turkish"
                            },
                            "italian-url": {
                                da: "danish",
                                de: "german",
                                en: "english",
                                es: "spanish",
                                fr: "french",
                                id: "indonesian",
                                it: "italian",
                                nl: "dutch",
                                no: "norwegian",
                                pl: "polish",
                                pt: "portuguese",
                                ru: "russian",
                                sv: "swedish",
                                tr: "turkish"
                            },
                            "an Italian": {
                                da: "a Danish",
                                de: "a German",
                                en: "an English",
                                es: "a Spanish",
                                fr: "a French",
                                id: "an Indonesian",
                                it: "an Italian",
                                nl: "a Dutch",
                                no: "a Norwegian",
                                pl: "a Polish",
                                pt: "a Portuguese",
                                ru: "a Russian",
                                sv: "a Swedish",
                                tr: "a Turkish"
                            },
                            "Business Italian": {
                                da: "Business Danish",
                                de: "Business German",
                                en: "Business English",
                                es: "Business Spanish",
                                fr: "Business French",
                                id: "Business Indonesian",
                                it: "Business Italian",
                                nl: "Business Dutch",
                                no: "Business Norwegian",
                                pl: "Business Polish",
                                pt: "Business Portuguese",
                                ru: "Business Russian",
                                sv: "Business Swedish",
                                tr: "Business Turkish"
                            },
                            "business-italian-url": {
                                da: "business-danish",
                                de: "business-german",
                                en: "business-english",
                                es: "business-spanish",
                                fr: "business-french",
                                id: "business-indonesian",
                                it: "business-italian",
                                nl: "business-dutch",
                                no: "business-norwegian",
                                pl: "business-polish",
                                pt: "business-portuguese",
                                ru: "business-russian",
                                sv: "business-swedish",
                                tr: "business-turkish"
                            }
                        }
                    }
                },
                pl: {
                    interpolations: {
                        language: {
                            "w\u0142osku": {
                                da: "du\u0144sku",
                                de: "niemiecku",
                                en: "angielsku",
                                es: "hiszpa\u0144sku",
                                fr: "francusku",
                                id: "indonezyjsku",
                                it: "w\u0142osku",
                                nl: "holendersku",
                                no: "norwesku",
                                pl: "polsku",
                                pt: "portugalsku",
                                ru: "rosyjsku",
                                sv: "szwedzku",
                                tr: "turecku"
                            },
                            "w\u0142oskiego": {
                                da: "du\u0144skiego",
                                de: "niemieckiego",
                                en: "angielskiego",
                                es: "hiszpa\u0144skiego",
                                fr: "francuskiego",
                                id: "indonezyjskiego",
                                it: "w\u0142oskiego",
                                nl: "holenderskiego",
                                no: "norweskiego",
                                pl: "polskiego",
                                pt: "portugalskiego",
                                ru: "rosyjskiego",
                                sv: "szwedzkiego",
                                tr: "tureckiego"
                            },
                            "w\u0142oski": {
                                da: "du\u0144ski",
                                de: "niemiecki",
                                en: "angielski",
                                es: "hiszpa\u0144ski",
                                fr: "francuski",
                                id: "indonezyjski",
                                it: "w\u0142oski",
                                nl: "holenderski",
                                no: "norweski",
                                pl: "polski",
                                pt: "portugalski",
                                ru: "rosyjski",
                                sv: "szwedzki",
                                tr: "turecki"
                            },
                            "w\u0142oskie": {
                                da: "du\u0144skie",
                                de: "niemieckie",
                                en: "angielskie",
                                es: "hiszpa\u0144skie",
                                fr: "francuskie",
                                id: "indonezyjskie",
                                it: "w\u0142oskie",
                                nl: "holenderskie",
                                no: "norweskie",
                                pl: "polskie",
                                pt: "portugalskie",
                                ru: "rosyjskie",
                                sv: "szwedzkie",
                                tr: "tureckie"
                            },
                            "w\u0142oskim": {
                                da: "du\u0144skim",
                                de: "niemieckim",
                                en: "angielskim",
                                es: "hiszpa\u0144skim",
                                fr: "francuskim",
                                id: "indonezyjskim",
                                it: "w\u0142oskim",
                                nl: "holenderskim",
                                no: "norweskim",
                                pl: "polskim",
                                pt: "portugalskim",
                                ru: "rosyjskim",
                                sv: "szwedzkim",
                                tr: "tureckim"
                            },
                            "w\u0142oski (Standard)": {
                                da: "du\u0144ski",
                                de: "niemiecki",
                                en: "angielski",
                                es: "hiszpa\u0144ski",
                                fr: "francuski",
                                id: "indonezyjski",
                                it: "w\u0142oski",
                                nl: "holenderski",
                                no: "norweski (bokm\xe5l)",
                                pl: "polski",
                                pt: "portugalski",
                                ru: "rosyjski",
                                sv: "szwedzki",
                                tr: "turecki"
                            }
                        }
                    }
                }
            }, window.I18n.interpolations = function (n) {
                var t, a, i, r, s, o;
                for (a in null == n && (n = {}), r = {}, n)
                    for (t in o = n[a], i = e[I18n.locale].interpolations[a])
                        i[t], s = e[I18n.locale].interpolations[a][t][o], r[t] = s, t.substring(0, 1).toUpperCase() !== t.substring(0, 1) && (r[t.substring(0, 1).toUpperCase() + t.substring(1)] = s.substring(0, 1).toUpperCase() + s.substring(1));
                return r
            }
        }.call(this), b3.extend({
    popup: function (e, n, t, a, i) {
        var r = {
            htmlContent: e,
            width: n,
            overlay: "",
            popup: "",
            afterCB: null,
            afterRemove: function () {
                r.afterCB && "function" == typeof r.afterCB && r.afterCB()
            },
            setAfterRemove: function (e) {
                r.afterCB = e
            },
            show: function () {
                if (r.overlay = $('<div class="popup-background"/>'), $("body").append(r.overlay), r.popup = $('<div class="popup"/>'), r.popup.append(r.htmlContent), !0 === t) {
                    var e = $('<a class="popup-close"/>');
                    e.click(r.remove), r.popup.append(e)
                }
                if (i) {
                    var n = $('<a class="popupheader"/>').html(i);
                    r.popup.prepend(n)
                }
                null !== r.width && r.popup.width(r.width), b3.stage.baseContainer().append(r.popup), r.popup.css({
                    marginTop: "-" + r.popup.outerHeight() / 2 + "px",
                    marginLeft: "-" + r.popup.outerWidth() / 2 + "px",
                    position: "absolute",
                    left: "50%",
                    top: "50%"
                }), a || r.popup.click(r.remove), r.overlay.click(r.remove)
            },
            remove: function () {
                r.popup.remove(), r.overlay.remove(), r.afterRemove()
            }
        };
        this.show = r.show, this.remove = r.remove, this.afterRemove = r.setAfterRemove
    }
}), b3.extend({
    footerEU2: function () {
        var e = {
            html: "",
            getContainer: function () {
                e.html = $(".footer:first")
            },
            show: function () {
                e.getContainer(), "USA" !== b3.settings.geoData.country && e.html.show(), e.onWindowResize(), $(window).on("resize", e.onWindowResize)
            },
            onWindowResize: function () {
                e.html.css("left", $("body").width() / 2 - e.html.outerWidth() / 2)
            }
        };
        this.show = e.show
    }
}), b3.extend({
    pageFunction: function (e) {
        var n = {
            prepared: !0,
            startFunction: e,
            trackingName: "pageFunction",
            prepare: function () {},
            start: function () {
                n.startFunction()
            }
        };
        this.start = n.start, this.prepare = n.prepare, this.prepared = function () {
            return n.prepared
        }, this.trackingName = function () {
            return n.trackingName
        }
    }
}), b3.extend({
    pageRegister1: function (e) {
        var n = {
            hideTitle: e,
            html: "",
            input: "",
            button: "",
            error: "",
            prepared: !1,
            submitEnabled: !0,
            trackingName: "Register1_firstname",
            prepare: function () {
                n.createHtml()
            },
            start: function () {
                b3.stage.appendWithFadeIn(n.html), n.input.focus(), b3.retargeting.setTagDemoEnd()
            },
            finish: function () {
                b3.retargeting.setTagNameEntry(), b3.stage.removeWithFadeOut(n.html, function () {
                    b3.sequence.showNextPage()
                })
            },
            createHtml: function () {
                n.prepared = !0, n.formClasses = {
                    firstname: "firstname-section"
                };
                var e = {
                    inputPlaceHolder: b3.t("page_register1.first_name"),
                    continueText: b3.t("page_register1.continue"),
                    registerClass: n.formClasses.firstname
                };
                "hideTitle" == n.hideTitle ? e.heading = b3.t("page_register1.please_save_your_result_now_to_continue_learning") : (e.heading = b3.t("page_register1.very_good_you_have_completed_the_first_round"), e.subHeading = b3.t("page_register1.please_save_your_result_now_to_continue_learning")), n.html = $(HandlebarsTemplates["demo/page_register"](e)), n.input = n.html.find("." + n.formClasses.firstname + " input"), n.input.keyup(function (e) {
                    b3.sharedBehaviours.registrationPopup.resetForm(n.formClasses), 13 === e.keyCode && (n.input.blur(), n.validate())
                }), n.button = n.html.find(".btn"), n.button.on("click", n.validate)
            },
            validate: function () {
                if (!1 !== n.submitEnabled) {
                    n.submitEnabled = !1, b3.sharedBehaviours.registrationPopup.resetForm(n.formClasses);
                    var e = {
                        user: {
                            firstname: n.input.val()
                        }
                    },
                            t = new b3.registrationUserApi.createDryRun(e);
                    t.call.done(n.saveStatusAndContinue.bind(n, e.user)), t.call.internalError(n.saveStatusAndContinue.bind(n, e.user)), t.call.validationSuccess(n.saveStatusAndContinue.bind(n, e.user)), t.call.validationFailure(function (e) {
                        e.className = n.formClasses[e.attribute], b3.sharedBehaviours.registrationPopup.showError(e), n.submitEnabled = !0, n.input.focus()
                    })
                }
            },
            saveStatusAndContinue: function (e) {
                $.extend(b3.settings, e), n.finish()
            }
        };
        this.start = n.start, this.prepare = n.prepare, this.prepared = function () {
            return n.prepared
        }, this.trackingName = function () {
            return n.trackingName
        }
    }
}), b3.extend({
    pageRegister2: function (e) {
        var n = {
            items: e,
            html: "",
            left: "",
            right: "",
            prepared: !1,
            submitEnabled: !0,
            trackingName: "Register2_email",
            prepare: function () {
                n.createHtml()
            },
            start: function () {
                b3.stage.appendWithFadeIn(n.html), n.input.focus()
            },
            finish: function () {
                b3.retargeting.setTagEmailEntry(), b3.stage.removeWithFadeOut(n.html, function () {
                    b3.sequence.showNextPage()
                })
            },
            createHtml: function () {
                n.prepared = !0, n.formClasses = {
                    email: "email-section"
                };
                var e = {
                    heading: b3.t("page_register2.please_enter_your_email"),
                    subHeading: b3.t("page_register2.this_is_absolutely_free"),
                    inputPlaceHolder: b3.t("page_register2.your_email"),
                    continueText: b3.t("page_register2.continue"),
                    registerClass: n.formClasses.email
                };
                n.html = $(HandlebarsTemplates["demo/page_register"](e)), n.input = n.html.find("." + n.formClasses.email + " input"), n.input.attr("id", "email"), n.input.keyup(function (e) {
                    b3.sharedBehaviours.registrationPopup.resetForm(n.formClasses), 13 === e.keyCode && (n.input.blur(), n.validate())
                }), n.button = n.html.find("." + n.formClasses.email + " .btn"), n.button.on("click", n.validate)
            },
            validate: function () {
                if (!1 !== n.submitEnabled) {
                    n.submitEnabled = !1, b3.sharedBehaviours.registrationPopup.resetForm(n.formClasses);
                    var e = {
                        user: {
                            email: n.input.val()
                        }
                    },
                            t = new b3.registrationUserApi.createDryRun(e);
                    t.call.done(n.saveStatusAndContinue.bind(n, e.user)), t.call.internalError(n.saveStatusAndContinue.bind(n, e.user)), t.call.validationSuccess(n.saveStatusAndContinue.bind(n, e.user)), t.call.validationFailure(function (e) {
                        e.className = n.formClasses[e.attribute], b3.sharedBehaviours.registrationPopup.showError(e), n.submitEnabled = !0, n.input.focus()
                    })
                }
            },
            saveStatusAndContinue: function (e) {
                $.extend(b3.settings, e), n.finish()
            }
        };
        this.start = n.start, this.prepare = n.prepare, this.prepared = function () {
            return n.prepared
        }, this.trackingName = function () {
            return n.trackingName
        }
    }
}), b3.extend({
    pageRegister3: function (e) {
        var n = {
            items: e,
            html: "",
            left: "",
            right: "",
            prepared: !1,
            submitEnabled: !0,
            trackingName: "Register3_password",
            prepare: function () {
                n.createHtml()
            },
            start: function () {
                b3.stage.appendWithFadeIn(n.html), n.input.focus()
            },
            finish: function () {
                b3.retargeting.setTagNamePasswordEntry(), b3.stage.removeWithFadeOut(n.html, function () {
                    b3.sequence.showNextPage()
                })
            },
            createHtml: function () {
                n.prepared = !0, n.formClasses = {
                    password: "password-section",
                    privacy_policy: "privacy-policy-section"
                };
                var e = {
                    heading: b3.t("page_register3.please_choose_a_password"),
                    subHeading: b3.t("page_register3.minimum_password_length"),
                    inputPlaceHolder: b3.t("page_register3.your_password"),
                    continueText: b3.t("page_register3.continue"),
                    registerClass: n.formClasses.password,
                    isPassword: !0
                };
                e.privacyPolicyText = n.privacyPolicy(), e.termsAndConditionsText = n.termsAndConditions(), e.newsletterText = n.newsletter(), n.html = $(HandlebarsTemplates["demo/page_register"](e)), n.attachPrivacyPolicy(), n.button = n.html.find(".btn"), n.button.on("click", n.validate), n.input = n.html.find("input[type=password]"), n.input.keyup(function (e) {
                    b3.sharedBehaviours.registrationPopup.resetForm(n.formClasses), 13 === e.keyCode && (n.input.blur(), n.validate())
                }), n.privacyPolicy = n.html.find("input[type=checkbox]")
            },
            newsletter: function () {
                if (n.handleNewsletterContent())
                    return b3.t("page_register3.gdpr_content")
            },
            handleNewsletterContent: function () {
                var e, n = ["AUT", "BEL", "BGR", "HRV", "CYP", "CZE", "DNK", "EST", "FRA", "DEU", "GRC", "HUN", "IRL", "ITA", "LVA", "LTU", "LUX", "MLT", "NLD", "ANT", "NOR", "POL", "PRT", "ROU", "SVK", "SVN", "ESP", "SWE", "CHE", "GBR"];
                return "" !== b3.settings.geoData.country && (e = -1 !== n.indexOf(b3.settings.geoData.country)), e
            },
            privacyPolicy: function () {
                return b3.t("page_register3.privacy_policy_checkbox_html", null, {
                    privacyLink: b3.t("legal.privacy.url", null, "", "company")
                })
            },
            termsAndConditions: function () {
                return b3.t("page_register3.terms_hint_html", null, {
                    termsLink: b3.t("legal.terms.url", null, "", "company")
                })
            },
            attachPrivacyPolicy: function () {
                var e = n.html.find(".privacy-policy");
                e.find("a:eq(0)").on("click", n.onPrivacyClick), e.find("a:eq(1)").on("click", function () {
                    return n.onTermClick("click_terms_link_checkbox", $(this)), !1
                })
            },
            onTermClick: function (e, n) {
                n ? window.open($(n).prop("href")) : window.open(b3.t("legal.terms.url", null, "", "company"))
            },
            onPrivacyClick: function () {
                return window.open($(this).prop("href")), !1
            },
            validate: function () {
                if (!1 !== n.submitEnabled) {
                    n.submitEnabled = !1, b3.sharedBehaviours.registrationPopup.resetForm(n.formClasses);
                    var e = {
                        user: {
                            password: n.input.val(),
                            privacy_policy: n.privacyPolicy.is(":checked"),
                            newsletter: "true",
                            terms_and_conditions: !0
                        }
                    },
                            t = new b3.registrationUserApi.createDryRun(e);
                    t.call.done(n.saveStatusAndContinue.bind(n, e.user)), t.call.internalError(n.saveStatusAndContinue.bind(n, e.user)), t.call.validationSuccess(n.saveStatusAndContinue.bind(n, e.user)), t.call.validationFailure(function (e) {
                        e.className = n.formClasses[e.attribute], b3.sharedBehaviours.registrationPopup.showError(e), n.submitEnabled = !0, n.input.focus()
                    })
                }
            },
            saveStatusAndContinue: function (e) {
                $.extend(b3.settings, e), n.finish()
            }
        };
        this.start = n.start, this.prepare = n.prepare, this.prepared = function () {
            return n.prepared
        }, this.trackingName = function () {
            return n.trackingName
        }
    }
}), b3.extend({
    pageRegisterSubmit: function () {
        var e = {
            html: "",
            prepared: !0,
            trackingName: "RegisterSubmit",
            prepare: function () {
                e.createHtml()
            },
            start: function () {
                b3.stage.appendWithFadeIn(e.html), e.register()
            },
            register: function () {
                var e = {
                    user: {
                        firstname: b3.settings.firstname,
                        email: b3.settings.email,
                        password: b3.settings.password,
                        learn_language_alpha3: b3.settings.currentLanguage,
                        privacy_policy: !0,
                        terms_and_conditions: !0
                    }
                };
                b3.settings.urlVars.postVerificationUrl && (e.registration_options = {
                    post_verification_redirect_url: decodeURIComponent(b3.settings.urlVars.postVerificationUrl),
                    immediate_verification_email_advised: !0
                }), b3.topic("stage.redirect").publish();
                var n = new b3.registrationUserApi.create(e);
                n.call.done(function (e) {
                    b3.trackingService.trackSimpleLead(e.user), window.setTimeout(function () {
                        b3.sharedBehaviours.registrationPage.signInUser(e)
                    }, 2e3)
                }), n.call.internalError(b3.sharedBehaviours.registrationPage.redirectTo500), n.call.validationFailure(b3.sharedBehaviours.registrationPage.showError)
            },
            finish: function () {},
            createHtml: function () {
                e.prepared = !0;
                var n = {
                    heading: b3.t("page_register_submit.we_are_creating_your_account")
                };
                e.html = $(HandlebarsTemplates["demo/page_register_submit"](n)), e.button = e.html.find(".btn"), e.button.on("click", e.register)
            }
        };
        this.start = e.start, this.prepare = e.prepare, this.prepared = function () {
            return e.prepared
        }, this.trackingName = function () {
            return e.trackingName
        }
    }
}), b3.extend({
    pageQuestionnaire: function (e, n) {
        var t = {
            pageTitle: e,
            questions: n,
            questionSlides: [],
            currentIndex: -1,
            html: "",
            prepared: !0,
            trackingName: "pq",
            prepare: function () {
                t.createHtml(), t.createQuestionSlides()
            },
            createQuestionSlides: function () {
                for (index in t.questions)
                    t.questionSlides.push(new b3.pageQuestionnaireSlide(t, index))
            },
            start: function () {
                if (t.skipPage())
                    return b3.stage.remove(t.html), void b3.sequence.showNextPage();
                t.showNextSlide(), b3.stage.appendWithFadeIn(t.html)
            },
            finish: function () {
                b3.stage.removeWithFadeOut(t.html, function () {
                    b3.sequence.showNextPage()
                })
            },
            skipPage: function () {
                var e, n = (b3.cookie.get("pq") || "").split(":"),
                        a = !0;
                for (e in t.questions)
                    -1 === $.inArray(t.questions[e].questionTrackingName, n) && (a = !1);
                return a = a && "true" !== b3.settings.urlVars.noPopup
            },
            showNextSlide: function () {
                t.currentIndex++, t.currentIndex !== t.questionSlides.length ? t.questionSlides[t.currentIndex].start() : t.finish()
            },
            createHtml: function () {
                t.html = $('<div class="pageComponent page-questionnaire endpage"/>'), t.html.append($('<h1 class="general-heading">' + t.pageTitle + "</h1>"))
            }
        };
        this.start = t.start, this.prepare = t.prepare, this.prepared = function () {
            return t.prepared
        }, this.trackingName = function () {
            return t.trackingName
        }
    }
}), b3.extend({
    pageQuestionnaireSlide: function (e, n) {
        var t = {
            pageQuestionnaire: e,
            html: "",
            index: n,
            question: e.questions[n],
            answer: "",
            textarea: "",
            baseTracking: (e.trackingName || "") + "_",
            prepare: function () {
                var e;
                t.html = $('<div class="page-questionnaire-slide" />'), t.html.append($('<h1 class="main-heading">' + t.question.question + "</h1>"));
                var n = $('<div class="babbel-button-container"/>');
                t.html.append(n);
                for (var a = 0; a < t.question.answers.length; a++) {
                    var i = t.question.answers[a];
                    "<br>" === i ? n.append($("<br>")) : "textarea" === i ? (t.textarea = $("<textarea/>"), n.append(t.textarea)) : "string" == typeof i ? (e = new b3.babbelButton(i, "btn btn-info"), t.appendButtonClick(e, i), n.append(e)) : null !== i.label && (e = new b3.babbelButton(i.label, "btn btn-info"), t.appendButtonClick(e, i), n.append(e))
                }
            },
            appendButtonClick: function (e, n) {
                t.question.question.replace(/ /g, "_"), e.click(function () {
                    "" !== t.textarea || (null !== n.tracking ? t.answer = n.tracking.replace(/ /g, "_") : null !== n.tracking ? t.answer = n.tracking.replace(/ /g, "_") : null !== n.label ? t.answer = n.label.replace(/ /g, "_") : "string" == typeof n && (t.answer = n.replace(/ /g, "_"))), "" !== t.answer && "undefined" != typeof t.question.questionTrackingName && b3.trackingService.trackQuestionAnswered({
                        question: t.question.questionTrackingName,
                        answer: t.answer
                    }), b3.eventsTracker.track({
                        name: "question:answered",
                        version: 4,
                        payload: {
                            provider: "lp.babbel",
                            question: t.question.questionTrackingNameV2.question,
                            answer_presentation_id: t.question.questionTrackingNameV2.answer_presentation_id,
                            answer: n.trackingV2
                        }
                    }), $(this).addClass("disabled"), $(this).unbind("click"), setTimeout(t.finish, 500)
                })
            },
            start: function () {
                return t.showSlide() ? "function" == typeof t.question.startFunction && !1 === t.question.startFunction() ? (t.question.finishFunction = null, void t.finish()) : (t.pageQuestionnaire.html.append(t.html), b3.eventsTracker.track({
                    name: "question:asked",
                    version: 4,
                    payload: {
                        provider: "lp.babbel",
                        question: t.question.questionTrackingNameV2.question,
                        answer_presentation_id: t.question.questionTrackingNameV2.answer_presentation_id
                    }
                }), n > 0 && (t.html.hide(), t.html.fadeIn(1e3)), void setTimeout(function () {
                    "" !== t.textarea && t.textarea.focus()
                }, 100)) : (t.question.finishFunction = null, void t.finish())
            },
            showSlide: function () {
                var e, n = (b3.cookie.get("pq") || "").split(":"),
                        a = !0;
                for (e = 0; e < n.length; e++)
                    n[e] === t.question.questionTrackingName && (a = !1);
                return a = a || "true" === b3.settings.urlVars.noPopup
            },
            showedSlide: function () {
                var e = (b3.cookie.get("pq") || "").split(":");
                e.push(t.question.questionTrackingName), e = $.unique(e), e = $.map(e, function (e) {
                    return "" === e ? null : e
                }), b3.cookie.set("pq", e.join(":"))
            },
            finish: function () {
                t.showedSlide(), "function" == typeof t.question.finishFunction && !1 === t.question.finishFunction(t.answer) || (t.index <= e.questions.length - 1 && t.html.remove(), t.pageQuestionnaire.showNextSlide())
            }
        };
        t.prepare(), this.start = t.start
    }
}), b3.extend({
    popupSequence: function () {
        var e = {
            sequence: [],
            selectedIndex: -1,
            setSequence: function (n) {
                e.sequence = n
            },
            startSequence: function () {
                e.sequence[0].prepare(), e.showNextPage()
            },
            cleanupCurrentPage: function () {
                $(".modal-backdrop").click()
            },
            showNextPage: function (n) {
                var t, a, i, r = 50;
                e.cleanupCurrentPage(), e.selectedIndex++, t = e.sequence[e.selectedIndex], a = e.sequence[e.selectedIndex + 1], e.selectedIndex >= e.sequence.length || ("function" == typeof t.trackingName && t.trackingName(), a && a.prepare(), "skipNextPage" !== n ? i = setInterval(function () {
                    --r <= 0 && (clearInterval(i), t.start()), t.prepared() && (clearInterval(i), t.start())
                }, 100) : e.showNextPage())
            }
        };
        this.startSequence = e.startSequence, this.showNextPage = e.showNextPage, this.setSequence = e.setSequence
    }
}), b3.extend({
    pageQuestionnairePopup: function (e, n, t) {
        var a = {
            pageTitle: e,
            questions: n,
            questionSlides: [],
            currentIndex: -1,
            html: "",
            prepared: !1,
            trackingName: "pqPopup",
            popup: null,
            sequencer: t,
            prepare: function () {
                a.createHtml(), a.createQuestionSlides(), a.prepared = !0
            },
            createQuestionSlides: function () {
                for (index in a.questions)
                    a.questionSlides.push(new b3.pageQuestionnaireSlide(a, index))
            },
            start: function () {
                a.showNextSlide()
            },
            finish: function () {
                a.popup && (a.popup.modal("hide"), a.popup = null), a.sequencer && a.sequencer.showNextPage()
            },
            showNextSlide: function () {
                a.currentIndex++, a.currentIndex === a.questionSlides.length ? a.finish() : a.updatePopup(a.currentIndex)
            },
            createHtml: function () {
                a.html = $('<div class="page-questionnaire-popup"/>'), a.html.append($('<h3 class="general-title">' + a.pageTitle + "</h3>"))
            },
            createPopup: function () {
                a.currentIndex >= a.questionSlides.length || (a.popup = $(b3.templates("core.modal")({
                    id: "pqPopup"
                })).modal({
                    backdrop: "static"
                }), a.popup.on("shown", function () {
                    $("#pqPopup .modal-body").html(a.html)
                }), a.popup.on("hidden", function () {
                    a.sequencer && a.sequencer.showNextPage()
                }))
            },
            updatePopup: function (e) {
                var t = a.questionSlides[e],
                        i = $("#pqPopup");
                t.start(), null === a.popup && (a.createPopup(), (i = $("#pqPopup")).find(".modal-body").html(a.html)), n[e].sizeWidth && i.css("width", n[e].sizeWidth)
            }
        };
        this.start = a.start, this.prepare = a.prepare, this.prepared = function () {
            return a.prepared
        }, this.trackingName = function () {
            return a.trackingName
        }
    }
}), b3.extend({
    pageGenericPopup: function (e, n) {
        var t = {
            title: e.title,
            body: e.body,
            clazz: e.clazz || "",
            buttons: e.buttons || [],
            textarea: e.textarea,
            sizeWidth: e.sizeWidth || "35em",
            preCondition: e.preCondition,
            trackingName: e.trackingName || "pageGenericPopup",
            html: "",
            buttonsContainer: null,
            textareaSection: null,
            prepared: !0,
            popup: null,
            sequencer: n,
            prepare: function () {
                t.createHtml()
            },
            start: function () {
                t.preCondition && !1 === t.preCondition(t) ? t.finish() : null === t.popup && t.createPopup()
            },
            finish: function () {
                t.popup && (t.popup.modal("hide"), t.popup = null), t.sequencer && t.sequencer.showNextPage()
            },
            createPopup: function () {
                t.popup = $(b3.templates("core.modal")({
                    id: "pageGenericPopupContainer"
                })).modal({
                    backdrop: "static"
                }), t.popup.on("shown", function () {
                    $("#pageGenericPopupContainer .modal-body").html(t.html)
                }), t.popup.on("hidden", function () {
                    t.sequencer && t.sequencer.showNextPage()
                })
            },
            createHtml: function () {
                t.html = $('<div class="pageGenericPopup"/>'), t.html.addClass(t.clazz), t.html.append($("<h1>" + t.title + "</h1>")), $.isBlank(t.body) || t.html.append($("<h3>" + t.body + "</h3>"));
                var e = $('<div class="babbel-button-container"/>');
                t.html.append(e), t.buttonsContainer = e;
                for (var n = 0; n < t.buttons.length; n++) {
                    var a = new b3.babbelButton(t.buttons[n].label, t.buttons[n].cssClass);
                    a.data("tracking-name", t.buttons[n].trackingName), a.click(function () {
                        $(this).addClass("disabled")
                    }), "function" == typeof t.buttons[n].onClick ? a.click(t.buttons[n].onClick) : a.click(function () {
                        t.finish()
                    }), e.append(a)
                }
                if (t.textarea) {
                    var i, r = $('<div class="textarea-section" />'),
                            s = $("<textarea />");
                    t.textarea.title && r.append($('<p class="lead" />').html(t.textarea.title)), r.append(s), t.textarea.proceedButton && ((i = new b3.babbelButton(t.textarea.proceedButton.title, "btn btn-primary")).click(function () {
                        $(this).addClass("disabled")
                    }), "function" == typeof t.textarea.proceedButton.onClick ? i.click(t.textarea.proceedButton.onClick) : i.click(function () {
                        t.finish()
                    }), r.append(i)), t.html.append(r), !1 === t.textarea.hidden && r.show(), t.textareaSection = r
                }
            },
            showTextArea: function (e) {
                $.extend(e, {}), e.hideAllButtons && t.buttonsContainer && t.buttonsContainer.hide(), t.textareaSection.show()
            }
        };
        this.start = t.start, this.prepare = t.prepare, this.showTextArea = t.showTextArea, this.finish = t.finish, this.prepared = function () {
            return t.prepared
        }, this.trackingName = function () {
            return t.trackingName
        }
    }
}), b3.extend({
    pageGeneric1: function (e) {
        var n = {
            title: e.title,
            body: e.body,
            body2: e.body2,
            html: "",
            buttons: e.buttons,
            buttonsContainer: null,
            textarea: e.textarea,
            textareaSection: null,
            prepared: !0,
            trackingName: "pageGeneric1" + e.title,
            prepare: function () {
                n.createHtml()
            },
            start: function () {
                b3.stage.appendWithFadeIn(n.html)
            },
            finish: function () {
                b3.stage.removeWithFadeOut(n.html, function () {
                    b3.sequence.showNextPage()
                })
            },
            createHtml: function () {
                n.html = $('<div class="pageGeneric1"/>'), n.html.append($("<h1>" + n.title + "</h1>")), $.isBlank(n.body) || n.html.append($("<h3>" + n.body + "</h3>")), null != n.body2 && (n.html.append($("<hr/>")), n.html.append($("<h3>" + n.body2 + "</h3>")));
                var e = $('<div class="babbel-button-container"/>');
                for (i in n.html.append(e), n.buttonsContainer = e, n.buttons) {
                    var t = new b3.babbelButton(n.buttons[i].label);
                    t.click(function () {
                        $(this).addClass("disabled")
                    }), "function" == typeof n.buttons[i].onClick ? t.click(n.buttons[i].onClick) : t.click(n.finish), e.append(t)
                }
                if (n.textarea) {
                    var a, r = $('<div class="textarea-section" />'),
                            s = $("<textarea />");
                    r.append(s), n.textarea.proceedButton && ((a = new b3.babbelButton(n.textarea.proceedButton.title)).click(function () {
                        $(this).addClass("disabled")
                    }), "function" == typeof n.textarea.proceedButton.onClick ? a.click(n.textarea.proceedButton.onClick) : a.click(n.finish), r.append(a)), n.html.append(r), !1 === n.textarea.hidden && r.show(), n.textareaSection = r
                }
            },
            showTextArea: function (e) {
                $.extend(e, {}), e.hideAllButtons && n.buttonsContainer && n.buttonsContainer.hide(), n.textareaSection.show()
            }
        };
        this.start = n.start, this.prepare = n.prepare, this.showTextArea = n.showTextArea, this.finish = n.finish, this.prepared = function () {
            return n.prepared
        }, this.trackingName = function () {
            return n.trackingName
        }
    }
}), b3.extend({
    skipPageLink: function () {
        var e = {
            html: null,
            container: "skip_page",
            question: "Warum willst du diese Seite \xfcberspringen?",
            skipPopup: !1,
            textarea: null,
            popup: null,
            continueButton: null,
            init: function () {
                e.createHtml(), $(b3.stage.baseContainer()).append(e.html)
            },
            createHtml: function () {
                var n = $('<a class="skip_page" />').html("Seite \xfcberspringen").click(e.onClick);
                e.html = n
            },
            show: function () {
                null != e.html && e.html.show()
            },
            hide: function () {
                null != e.html && e.html.hide()
            },
            onClick: function () {
                0 == e.skipPopup ? e.showPopup() : setTimeout(b3.sequence.showNextPage, 300)
            },
            showPopup: function () {
                var n, t, a, i, r;
                for (e.skipPopup = !0, (n = $('<div class="skipPageLinkPopUp" />')).append($('<h1 class="main-heading" />').html(e.question)), t = $('<div class="babbel-button-container"/>'), n.append(t), r = ["Zu einfach", "Zu schwer", "Technische Probleme", "Keine Zeit mehr", "Zu langweilig", "Sonstiges"], i = 0; i < r.length; ++i)
                    a = new b3.babbelButton(r[i], "btn btn-info"), t.append(a), a.click(e.onPopupButtonClick);
                e.popup = $(b3.templates("core.modal")({
                    id: "pageGenericPopupContainer"
                })).modal({
                    backdrop: "static"
                }), e.popup.on("shown", function () {
                    $("#pageGenericPopupContainer .modal-body").html(n)
                })
            },
            onPopupButtonClick: function () {
                e.popup.modal("hide"), b3.sequence.showNextPage()
            }
        };
        this.show = e.show, this.hide = e.hide, this.init = e.init
    }
}), b3.skipPageLink = new b3.skipPageLink, b3.extend({
    staticChooseReferenceLanguage5: function () {
        var e = {
            html: "",
            prepared: !0,
            prepare: function () {
                e.createHtml()
            },
            start: function () {
                e.prepare(), b3.stage.append(e.html)
            },
            createHtml: function () {
                var n = "";
                e.prepared = !0, e.html = $('<div class="pageComponent staticChooseReferenceLanguage5"/>'), e.html.append($(b3.t("static_choose_reference_language3.please_choose_your_mother_tongue", "h1")));
                for (var t = [{
                        t: b3.t("static_choose_reference_language3.language", null, I18n.interpolations({
                            language: "tr"
                        })),
                        l: "TUR"
                    }, {
                        t: b3.t("static_choose_reference_language3.language", null, I18n.interpolations({
                            language: "en"
                        })),
                        l: "ENG"
                    }, {
                        t: b3.t("static_choose_reference_language3.language", null, I18n.interpolations({
                            language: "fr"
                        })),
                        l: "FRA"
                    }, {
                        t: b3.t("static_choose_reference_language3.greek"),
                        l: "GRE"
                    }, {
                        t: b3.t("static_choose_reference_language3.language", null, I18n.interpolations({
                            language: "it"
                        })),
                        l: "ITA"
                    }, {
                        t: b3.t("static_choose_reference_language3.language", null, I18n.interpolations({
                            language: "nl"
                        })),
                        l: "NLD"
                    }, {
                        t: b3.t("static_choose_reference_language3.language", null, I18n.interpolations({
                            language: "pl"
                        })),
                        l: "POL"
                    }, {
                        t: b3.t("static_choose_reference_language3.language", null, I18n.interpolations({
                            language: "pt"
                        })),
                        l: "POR"
                    }, {
                        t: b3.t("static_choose_reference_language3.russian"),
                        l: "RUS"
                    }, {
                        t: b3.t("static_choose_reference_language3.romanian"),
                        l: "RUM"
                    }, {
                        t: b3.t("static_choose_reference_language3.bulgarian"),
                        l: "BUL"
                    }, {
                        t: b3.t("static_choose_reference_language3.serbian"),
                        l: "SER"
                    }, {
                        t: b3.t("static_choose_reference_language3.hungarian"),
                        l: "UNG"
                    }, {
                        t: b3.t("static_choose_reference_language3.language", null, I18n.interpolations({
                            language: "es"
                        })),
                        l: "SPA"
                    }, {
                        t: b3.t("static_choose_reference_language3.language", null, I18n.interpolations({
                            language: "sv"
                        })),
                        l: "SWE"
                    }, {
                        t: b3.t("static_choose_reference_language3.language", null, I18n.interpolations({
                            language: "de"
                        })),
                        l: "DEU"
                    }], a = 0; a < t.length; a++)
                    n = new b3.babbelButton(t[a].t), e.html.append(n), n.click(function () {
                        for (var n = 0; n < t.length; n++)
                            if ($(this).html() === t[n].t) {
                                $(this).addClass("disabled"), $(this).unbind(), e.click(t[n].l);
                                break
                            }
                    }), 3 !== a && 7 !== a && 11 !== a || e.html.append("<br>")
            },
            click: function (e) {
                "DEU" === e ? b3.settings.currentLanguage = "ENG" : -1 === "ENG,FRA,ITA,SPA,SWE,POR".indexOf(e) && (e = "ENG"), b3.settings.referenceLanguage = e, setTimeout(function () {
                    b3.sequence.showNextPage()
                }, 500)
            },
            showMorePopup: function () {
                var n = $('<div class="feedbackPopup">');
                n.append(b3.t("static_choose_reference_language3.what_is_your_mother_tongue", "h3"));
                var t = $('<form class="staticChooseReferenceLanguage5_PopupForm" />'),
                        a = $('<input name="email" id="email" />'),
                        i = $('<input type="submit" class="babbelButton" value="' + b3.t("static_choose_reference_language3.continue") + "/>");
                t.append(a, i), n.append(t);
                var r = new b3.popup(n, "25em", !0, !0);
                r.show(), t.submit(function (n) {
                    n.preventDefault(), e.click("custom:" + a.val()), r.remove()
                }), a.focus()
            },
            onOtherLanguagesClick: function () {
                e.html.remove();
                var n = b3.settings.mergedUrlWithParams(b3.settings.guiLanguage + "_index.html", {
                    l2: "ALL",
                    ch: b3.settings.trafficChannel
                });
                setTimeout(function () {
                    window.location = n
                }, 500)
            }
        };
        this.start = e.start, this.prepared = function () {
            return e.prepared
        }, this.prepare = function () {}
    }
}), b3.extend({
    startTopMenu: function (e) {
        var n = {
            html: "",
            prepared: !0,
            trackingName: "startTopMenu",
            menu: e,
            selectedLabel: e.selectedLabel,
            items: e.items,
            selected: null,
            container: null,
            dropdown: null,
            prepare: function () {
                n.createHtml()
            },
            show: function () {
                n.prepare()
            },
            createHtml: function () {
                var e, t, a, i = n.items;
                e = $("<ul />").addClass("startTopMenu"), a = $("<li />").addClass("selectedItem").click(n.toggleDropDown), t = $("<ul />").addClass("topMenuItems").hide(), null !== n.selectedLabel && a.html(n.selectedLabel), $.each(i, function () {
                    null === n.selectedLabel && b3.settings.autostart === this.data && a.html(this.label);
                    var e = $("<li />");
                    e.data("data", this);
                    var i = e.append($("<a />").addClass("topMenuItem").html(this.label));
                    i.click(n.onButtonClick), t.append(i)
                }), e.append(a), e.append($("<li />").append(t)), n.container = e, n.selected = a, n.dropdown = t, $(".learningComponent").find("header.frame").prepend(e)
            },
            onButtonClick: function () {
                var e;
                n.toggleDropDown(), n.selected.html($(this).data("data").label), e = $(this).data("data").url, setTimeout(function () {
                    window.location = e
                }, 500)
            },
            toggleDropDown: function (e) {
                n.dropdown.toggle(), n.dropdown.is(":visible") && $("body").one("click", function () {
                    n.dropdown.hide()
                }), e && e.stopPropagation()
            }
        };
        this.show = n.show, this.prepared = function () {
            return n.prepared
        }, this.trackingName = function () {
            return n.trackingName
        }
    }
}), b3.extend({
    topMenu: function () {
        var e = {
            html: "",
            trackingName: "topMenu",
            prepare: function () {
                e.createHtml(), e.createItems()
            },
            show: function () {
                b3.settings.isValidLearnLanguage(b3.settings.currentLanguage) && $("header .frame-inner-right").html(e.html)
            },
            createItems: function () {
                e.addItem(new b3.topMenuLogin), e.addItem(new b3.topMenuSignUp)
            },
            addItem: function (n) {
                e.html.append(n)
            },
            createHtml: function () {
                e.html = $('<div class="topMenu"/>')
            }
        };
        this.show = e.show, e.prepare()
    }
}), b3.extend({
    topMenuSignUp: function () {
        var e = {
            html: "",
            popupHtml: null,
            popup: null,
            privacyPolicyCB: null,
            prepared: !1,
            submitEnabled: !0,
            trackingName: "topMenuSignUp",
            prepare: function () {
                e.createHtml()
            },
            finish: function () {
                b3.retargeting.setTagEmailEntry(), b3.retargeting.setTagNamePasswordEntry(), b3.topic("stage.redirect").publish(), setTimeout(function () {
                    var e = b3.settings.mergedUrlWithParams(b3.settings.referenceLanguage + "_register.html", {
                        l1: b3.settings.referenceLanguage,
                        l2: b3.settings.currentLanguage,
                        tutorialID: b3.settings.tutorialID,
                        ch: b3.settings.trafficChannel,
                        em: b3.settings.email,
                        fn: b3.settings.firstname
                    });
                    window.location = e
                }, 500)
            },
            createHtml: function () {
                e.html = $('<span class="topMenuSignUp" />'), e.registerButton = new b3.babbelButton(b3.t("topMenuSignUp.register", null), "btn btn-primary btn-small"), e.registerButton.on("click", e.showPopup), e.html.append(e.registerButton)
            },
            createPopupHtml: function () {
                if (e.formClasses = {
                    firstname: "firstname-section",
                    email: "email-section"
                }, null === e.popupHtml) {
                    var n = HandlebarsTemplates["demo/topmenu_signup"]({
                        heading: b3.t("topMenuSignUp.register"),
                        subHeading: b3.t("topMenuSignUp.save_your_learning_level_now_with_a_new_account_without_costs") + "<br>" + b3.t("topMenuSignUp.there_are_no_costs_and_you_can_continue_learning_on_your_next_visit"),
                        firstname: b3.t("topMenuSignUp.firstname"),
                        firstnameClassName: e.formClasses.firstname,
                        email: b3.t("topMenuSignUp.email"),
                        emailClassName: e.formClasses.email,
                        termsUrl: b3.t("legal.terms.url", null, "", "company"),
                        terms: b3.t("page_register3.terms_and_conditions"),
                        continueText: b3.t("topMenuSignUp.continue")
                    });
                    e.popupHtml = $(n), e.popupHtml.find("button.continue").on("click", e.validate), e.attachTerms()
                }
            },
            onTermClick: function () {
                window.open(b3.t("legal.terms.url", null, "", "company"))
            },
            showPopup: function () {
                e.createPopupHtml(), null === e.popup ? (e.popup = $(b3.templates("core.modal")({
                    id: "topMenuSignupPopup"
                })), e.popup.on("shown", function () {
                    $("#topMenuSignupPopup .modal-body").html(e.popupHtml)
                }), e.popup.modal({
                    backdrop: "static"
                })) : e.popup.modal("show")
            },
            attachTerms: function () {
                var n = e.popupHtml.find(".terms-and-conditions");
                "ENG" !== b3.settings.referenceLanguage && "DEU" !== b3.settings.referenceLanguage || n.hide(), n.on("click", e.onTermClick)
            },
            validate: function () {
                if (!1 !== e.submitEnabled) {
                    e.submitEnabled = !1;
                    var n = e.popupHtml.find("button.continue");
                    n.addClass("disabled"), b3.sharedBehaviours.registrationPopup.resetForm(e.formClasses);
                    var t = {
                        user: {
                            firstname: e.popupHtml.find(".firstname-section input").val(),
                            email: e.popupHtml.find(".email-section input").val()
                        }
                    },
                            a = new b3.registrationUserApi.createDryRun(t);
                    a.call.done(e.saveStatusAndContinue.bind(e, t.user)), a.call.internalError(e.saveStatusAndContinue.bind(e, t.user)), a.call.validationSuccess(e.saveStatusAndContinue.bind(e, t.user)), a.call.validationFailure(function (n) {
                        n.className = e.formClasses[n.attribute], b3.sharedBehaviours.registrationPopup.showError(n), e.submitEnabled = !0
                    }), a.call.always(function () {
                        n.removeClass("disabled")
                    })
                }
            },
            saveStatusAndContinue: function (n) {
                $.extend(b3.settings, n), e.finish()
            }
        };
        return this.show = e.show, this.trackingName = function () {
            return e.trackingName
        }, !1 === e.prepared && e.prepare(), e.html
    }
}), b3.extend({
    topMenuLogin: function () {
        var e = {
            html: "",
            prepared: !1,
            trackingName: "topMenuLogin",
            prepare: function () {
                e.createHtml()
            },
            createHtml: function () {
                e.html = $('<span class="topMenuLogin" />'), e.loginButton = new b3.babbelButton(b3.t("topMenuLogin.login", null), "btn btn-inverse btn-small"), e.loginButton.on("click", e.redirectToSignInPage), e.html.append(e.loginButton)
            },
            redirectToSignInPage: function () {
                window.location = b3.settings.urlFromBabbelDomain(b3.settings.referenceLanguageLocale() + "/accounts/sign_in", "accounts")
            }
        };
        return this.trackingName = function () {
            return e.trackingName
        }, !1 === e.prepared && e.prepare(), e.html
    }
}), b3.extend({
    retargeting: function () {
        var e = {
            setTagUnregisteredUser: function () {
                var e, n, t = b3.settings.referenceLanguage,
                        a = b3.settings.currentLanguage;
                new Image(1, 1).src = "//www.googleadservices.com/pagead/conversion/1015134565/?label=ZhMQCJvHwAIQ5fKG5AM&amp;guid=ON&amp;script=0&amp;timestamp=" + (new Date).getTime(), new Image(1, 1).src = "//www.googleadservices.com/pagead/conversion/1015134565/?label=hWlACJPIwAIQ5fKG5AM&amp;guid=ON&amp;script=0&amp;timestamp=" + (new Date).getTime(), new Image(1, 1).src = "//www.googleadservices.com/pagead/conversion/1015134565/?label=IzmRCIvJwAIQ5fKG5AM&amp;guid=ON&amp;script=0&amp;timestamp=" + (new Date).getTime(), new Image(1, 1).src = "//www.googleadservices.com/pagead/conversion/1015134565/?label=LRRRCIPKwAIQ5fKG5AM&amp;guid=ON&amp;script=0&amp;timestamp=" + (new Date).getTime(), e = "", "DEU" === t && (e = "cJ2yCKu2wAIQ5fKG5AM"), "ENG" === t && (e = "0RvcCKO3wAIQ5fKG5AM"), "FRA" === t && (e = "6aFwCJu4wAIQ5fKG5AM"), "ITA" === t && (e = "rvuOCJO5wAIQ5fKG5AM"), "POR" === t && (e = "NTR0CIu6wAIQ5fKG5AM"), "SPA" === t && (e = "Egw-CIO7wAIQ5fKG5AM"), "SWE" === t && (e = "OwesCPu7wAIQ5fKG5AM"), new Image(1, 1).src = "//www.googleadservices.com/pagead/conversion/1015134565/?label=" + e + "&amp;guid=ON&amp;script=0&amp;timestamp=" + (new Date).getTime(), n = "", "DEU" === a && (n = "fUU2CPO8wAIQ5fKG5AM"), "ENG" === a && (n = "l8WSCOu9wAIQ5fKG5AM"), "FRA" === a && (n = "MmI4COO-wAIQ5fKG5AM"), "ITA" === a && (n = "djPOCNu_wAIQ5fKG5AM"), "POR" === a && (n = "CpEuCNPAwAIQ5fKG5AM"), "SPA" === a && (n = "jeBZCMvBwAIQ5fKG5AM"), "SWE" === a && (n = "vGU0CMPCwAIQ5fKG5AM"), "IND" === a && (n = "JyVnCLvDwAIQ5fKG5AM"), "NLD" === a && (n = "Ctg2CLPEwAIQ5fKG5AM"), "POL" === a && (n = "r-uHCKvFwAIQ5fKG5AM"), "TUR" === a && (n = "B3-FCKPGwAIQ5fKG5AM"), new Image(1, 1).src = "//www.googleadservices.com/pagead/conversion/1015134565/?label=" + n + "&amp;guid=ON&amp;script=0&amp;timestamp=" + (new Date).getTime()
            },
            setTagDemoStart: function () {
                new Image(1, 1).src = "//googleads.g.doubleclick.net/pagead/viewthroughconversion/1015134565/?value=0&amp;label=zwDHCNu83AMQ5fKG5AM&amp;guid=ON&amp;script=0&amp;timestamp=" + (new Date).getTime(), new Image(1, 1).src = "//www.googleadservices.com/pagead/conversion/1015134565/?value=0&amp;label=zwDHCNu83AMQ5fKG5AM&amp;guid=ON&amp;script=0&amp;timestamp=" + (new Date).getTime()
            },
            setTagDemoEnd: function () {
                new Image(1, 1).src = "//googleads.g.doubleclick.net/pagead/viewthroughconversion/1015134565/?value=0&amp;label=zwdMCNO93AMQ5fKG5AM&amp;guid=ON&amp;script=0&amp;timestamp=" + (new Date).getTime(), new Image(1, 1).src = "//www.googleadservices.com/pagead/conversion/1015134565/?value=0&amp;label=zwdMCNO93AMQ5fKG5AM&amp;guid=ON&amp;script=0&amp;timestamp=" + (new Date).getTime()
            },
            setTagNameEntry: function () {
                new Image(1, 1).src = "//googleads.g.doubleclick.net/pagead/viewthroughconversion/1015134565/?value=0&amp;label=xzVZCMu-3AMQ5fKG5AM&amp;guid=ON&amp;script=0&amp;timestamp=" + (new Date).getTime(), new Image(1, 1).src = "//www.googleadservices.com/pagead/conversion/1015134565/?value=0&amp;label=xzVZCMu-3AMQ5fKG5AM&amp;guid=ON&amp;script=0&amp;timestamp=" + (new Date).getTime()
            },
            setTagEmailEntry: function () {
                new Image(1, 1).src = "//googleads.g.doubleclick.net/pagead/viewthroughconversion/1015134565/?value=0&amp;label=BaqUCMO_3AMQ5fKG5AM&amp;guid=ON&amp;script=0&amp;timestamp=" + (new Date).getTime(), new Image(1, 1).src = "//www.googleadservices.com/pagead/conversion/1015134565/?value=0&amp;label=BaqUCMO_3AMQ5fKG5AM&amp;guid=ON&amp;script=0&amp;timestamp=" + (new Date).getTime()
            },
            setTagNamePasswordEntry: function () {
                new Image(1, 1).src = "//googleads.g.doubleclick.net/pagead/viewthroughconversion/1015134565/?value=0&amp;label=ncU_CLPB3AMQ5fKG5AM&amp;guid=ON&amp;script=0&amp;timestamp=" + (new Date).getTime(), new Image(1, 1).src = "//www.googleadservices.com/pagead/conversion/1015134565/?value=0&amp;label=ncU_CLPB3AMQ5fKG5AM&amp;guid=ON&amp;script=0&amp;timestamp=" + (new Date).getTime()
            }
        };
        this.setTagUnregisteredUser = e.setTagUnregisteredUser, this.setTagDemoStart = e.setTagDemoStart, this.setTagDemoEnd = e.setTagDemoEnd, this.setTagNameEntry = e.setTagNameEntry, this.setTagEmailEntry = e.setTagEmailEntry, this.setTagNamePasswordEntry = e.setTagNamePasswordEntry
    }
}), b3.retargeting = new b3.retargeting;