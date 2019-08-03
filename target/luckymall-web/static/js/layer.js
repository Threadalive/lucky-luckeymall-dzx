/*! layer-v3.0.1 Web弹层组件 MIT License  http://layer.layui.com/  By 贤心 */
;!function (e, t) {
    "use strict";
    var i, n, a = e.layui && layui.define, o = {
        getPath: function () {
            var e = document.scripts, t = e[e.length - 1], i = t.src;
            if (!t.getAttribute("merge")) return i.substring(0, i.lastIndexOf("/") + 1)
        }(),
        config: {},
        end: {},
        minIndex: 0,
        minLeft: [],
        btn: ["&#x786E;&#x5B9A;", "&#x53D6;&#x6D88;"],
        type: ["dialog", "page", "iframe", "loading", "tips"]
    }, r = {
        v: "3.0.1", ie: function () {
            var t = navigator.userAgent.toLowerCase();
            return !!(e.ActiveXObject || "ActiveXObject" in e) && ((t.match(/msie\s(\d+)/) || [])[1] || "11")
        }(), index: e.layer && e.layer.v ? 1e5 : 0, path: o.getPath, config: function (e, t) {
            return e = e || {}, r.cache = o.config = i.extend({}, o.config, e), r.path = o.config.path || r.path, "string" == typeof e.extend && (e.extend = [e.extend]), o.config.path && r.ready(), e.extend ? (a ? layui.addcss("modules/layer/" + e.extend) : r.link("skin/" + e.extend), this) : this
        }, link: function (t, n, a) {
            if (r.path) {
                var o = i("head")[0], l = document.createElement("link");
                "string" == typeof n && (a = n);
                var s = (a || t).replace(/\.|\//g, ""), f = "layuicss-" + s, c = 0;
                l.rel = "stylesheet", l.href = r.path + t, l.id = f, i("#" + f)[0] || o.appendChild(l), "function" == typeof n && !function d() {
                    return ++c > 80 ? e.console && console.error("layer.css: Invalid") : void(1989 === parseInt(i("#" + f).css("width")) ? n() : setTimeout(d, 100))
                }()
            }
        }, ready: function (e) {
            var t = "skinlayercss", i = "1110";
            return a ? layui.addcss("modules/layer/default/layer.css?v=" + r.v + i, e, t) : r.link("skin/default/layer.css?v=" + r.v + i, e, t), this
        }, alert: function (e, t, n) {
            var a = "function" == typeof t;
            return a && (n = t), r.open(i.extend({content: e, yes: n}, a ? {} : t))
        }, confirm: function (e, t, n, a) {
            var l = "function" == typeof t;
            return l && (a = n, n = t), r.open(i.extend({content: e, btn: o.btn, yes: n, btn2: a}, l ? {} : t))
        }, msg: function (e, n, a) {
            var l = "function" == typeof n, f = o.config.skin, c = (f ? f + " " + f + "-msg" : "") || "layui-layer-msg",
                d = s.anim.length - 1;
            return l && (a = n), r.open(i.extend({
                content: e,
                time: 3e3,
                shade: !1,
                skin: c,
                title: !1,
                closeBtn: !1,
                btn: !1,
                resize: !1,
                end: a
            }, l && !o.config.skin ? {skin: c + " layui-layer-hui", anim: d} : function () {
                return n = n || {}, (n.icon === -1 || n.icon === t && !o.config.skin) && (n.skin = c + " " + (n.skin || "layui-layer-hui")), n
            }()))
        }, load: function (e, t) {
            return r.open(i.extend({type: 3, icon: e || 0, resize: !1, shade: .01}, t))
        }, tips: function (e, t, n) {
            return r.open(i.extend({
                type: 4,
                content: [e, t],
                closeBtn: !1,
                time: 3e3,
                shade: !1,
                resize: !1,
                fixed: !1,
                maxWidth: 210
            }, n))
        }
    }, l = function (e) {
        var t = this;
        t.index = ++r.index, t.config = i.extend({}, t.config, o.config, e), document.body ? t.creat() : setTimeout(function () {
            t.creat()
        }, 50)
    };
    l.pt = l.prototype;
    var s = ["layui-layer", ".layui-layer-title", ".layui-layer-main", ".layui-layer-dialog", "layui-layer-iframe", "layui-layer-content", "layui-layer-btn", "layui-layer-close"];
    s.anim = ["layer-anim", "layer-anim-01", "layer-anim-02", "layer-anim-03", "layer-anim-04", "layer-anim-05", "layer-anim-06"], l.pt.config = {
        type: 0,
        shade: .3,
        fixed: !0,
        move: s[1],
        title: "&#x4FE1;&#x606F;",
        offset: "auto",
        area: "auto",
        closeBtn: 1,
        time: 0,
        zIndex: 19891014,
        maxWidth: 360,
        anim: 0,
        icon: -1,
        moveType: 1,
        resize: !0,
        scrollbar: !0,
        tips: 2
    }, l.pt.vessel = function (e, t) {
        var n = this, a = n.index, r = n.config, l = r.zIndex + a, f = "object" == typeof r.title,
            c = r.maxmin && (1 === r.type || 2 === r.type),
            d = r.title ? '<div class="layui-layer-title" style="' + (f ? r.title[1] : "") + '">' + (f ? r.title[0] : r.title) + "</div>" : "";
        return r.zIndex = l, t([r.shade ? '<div class="layui-layer-shade" id="layui-layer-shade' + a + '" times="' + a + '" style="' + ("z-index:" + (l - 1) + "; background-color:" + (r.shade[1] || "#000") + "; opacity:" + (r.shade[0] || r.shade) + "; filter:alpha(opacity=" + (100 * r.shade[0] || 100 * r.shade) + ");") + '"></div>' : "", '<div class="' + s[0] + (" layui-layer-" + o.type[r.type]) + (0 != r.type && 2 != r.type || r.shade ? "" : " layui-layer-border") + " " + (r.skin || "") + '" id="' + s[0] + a + '" type="' + o.type[r.type] + '" times="' + a + '" showtime="' + r.time + '" conType="' + (e ? "object" : "string") + '" style="z-index: ' + l + "; width:" + r.area[0] + ";height:" + r.area[1] + (r.fixed ? "" : ";position:absolute;") + '">' + (e && 2 != r.type ? "" : d) + '<div id="' + (r.id || "") + '" class="layui-layer-content' + (0 == r.type && r.icon !== -1 ? " layui-layer-padding" : "") + (3 == r.type ? " layui-layer-loading" + r.icon : "") + '">' + (0 == r.type && r.icon !== -1 ? '<i class="layui-layer-ico layui-layer-ico' + r.icon + '"></i>' : "") + (1 == r.type && e ? "" : r.content || "") + '</div><span class="layui-layer-setwin">' + function () {
            var e = c ? '<a class="layui-layer-min" href="javascript:;"><cite></cite></a><a class="layui-layer-ico layui-layer-max" href="javascript:;"></a>' : "";
            return r.closeBtn && (e += '<a class="layui-layer-ico ' + s[7] + " " + s[7] + (r.title ? r.closeBtn : 4 == r.type ? "1" : "2") + '" href="javascript:;"></a>'), e
        }() + "</span>" + (r.btn ? function () {
            var e = "";
            "string" == typeof r.btn && (r.btn = [r.btn]);
            for (var t = 0, i = r.btn.length; t < i; t++) e += '<a class="' + s[6] + t + '">' + r.btn[t] + "</a>";
            return '<div class="' + s[6] + " layui-layer-btn-" + (r.btnAlign || "") + '">' + e + "</div>"
        }() : "") + (r.resize ? '<span class="layui-layer-resize"></span>' : "") + "</div>"], d, i('<div class="layui-layer-move"></div>')), n
    }, l.pt.creat = function () {
        var e = this, t = e.config, a = e.index, l = t.content, f = "object" == typeof l, c = i("body");
        if (!i("#" + t.id)[0]) {
            switch ("string" == typeof t.area && (t.area = "auto" === t.area ? ["", ""] : [t.area, ""]), t.shift && (t.anim = t.shift), 6 == r.ie && (t.fixed = !1), t.type) {
                case 0:
                    t.btn = "btn" in t ? t.btn : o.btn[0], r.closeAll("dialog");
                    break;
                case 2:
                    var l = t.content = f ? t.content : [t.content || "http://layer.layui.com", "auto"];
                    t.content = '<iframe scrolling="' + (t.content[1] || "auto") + '" allowtransparency="true" id="' + s[4] + a + '" name="' + s[4] + a + '" onload="this.className=\'\';" class="layui-layer-load" frameborder="0" src="' + t.content[0] + '"></iframe>';
                    break;
                case 3:
                    delete t.title, delete t.closeBtn, t.icon === -1 && 0 === t.icon, r.closeAll("loading");
                    break;
                case 4:
                    f || (t.content = [t.content, "body"]), t.follow = t.content[1], t.content = t.content[0] + '<i class="layui-layer-TipsG"></i>', delete t.title, t.tips = "object" == typeof t.tips ? t.tips : [t.tips, !0], t.tipsMore || r.closeAll("tips")
            }
            e.vessel(f, function (n, r, d) {
                c.append(n[0]), f ? function () {
                    2 == t.type || 4 == t.type ? function () {
                        i("body").append(n[1])
                    }() : function () {
                        l.parents("." + s[0])[0] || (l.data("display", l.css("display")).show().addClass("layui-layer-wrap").wrap(n[1]), i("#" + s[0] + a).find("." + s[5]).before(r))
                    }()
                }() : c.append(n[1]), i(".layui-layer-move")[0] || c.append(o.moveElem = d), e.layero = i("#" + s[0] + a), t.scrollbar || s.html.css("overflow", "hidden").attr("layer-full", a)
            }).auto(a), 2 == t.type && 6 == r.ie && e.layero.find("iframe").attr("src", l[0]), 4 == t.type ? e.tips() : e.offset(), t.fixed && n.on("resize", function () {
                e.offset(), (/^\d+%$/.test(t.area[0]) || /^\d+%$/.test(t.area[1])) && e.auto(a), 4 == t.type && e.tips()
            }), t.time <= 0 || setTimeout(function () {
                r.close(e.index)
            }, t.time), e.move().callback(), s.anim[t.anim] && e.layero.addClass(s.anim[t.anim]).data("anim", !0)
        }
    }, l.pt.auto = function (e) {
        function t(e) {
            e = l.find(e), e.height(f[1] - c - d - 2 * (0 | parseFloat(e.css("padding"))))
        }

        var a = this, o = a.config, l = i("#" + s[0] + e);
        "" === o.area[0] && o.maxWidth > 0 && (r.ie && r.ie < 8 && o.btn && l.width(l.innerWidth()), l.outerWidth() > o.maxWidth && l.width(o.maxWidth));
        var f = [l.innerWidth(), l.innerHeight()], c = l.find(s[1]).outerHeight() || 0,
            d = l.find("." + s[6]).outerHeight() || 0;
        switch (o.type) {
            case 2:
                t("iframe");
                break;
            default:
                "" === o.area[1] ? o.fixed && f[1] >= n.height() && (f[1] = n.height(), t("." + s[5])) : t("." + s[5])
        }
        return a
    }, l.pt.offset = function () {
        var e = this, t = e.config, i = e.layero, a = [i.outerWidth(), i.outerHeight()],
            o = "object" == typeof t.offset;
        e.offsetTop = (n.height() - a[1]) / 2, e.offsetLeft = (n.width() - a[0]) / 2, o ? (e.offsetTop = t.offset[0], e.offsetLeft = t.offset[1] || e.offsetLeft) : "auto" !== t.offset && ("t" === t.offset ? e.offsetTop = 0 : "r" === t.offset ? e.offsetLeft = n.width() - a[0] : "b" === t.offset ? e.offsetTop = n.height() - a[1] : "l" === t.offset ? e.offsetLeft = 0 : "lt" === t.offset ? (e.offsetTop = 0, e.offsetLeft = 0) : "lb" === t.offset ? (e.offsetTop = n.height() - a[1], e.offsetLeft = 0) : "rt" === t.offset ? (e.offsetTop = 0, e.offsetLeft = n.width() - a[0]) : "rb" === t.offset ? (e.offsetTop = n.height() - a[1], e.offsetLeft = n.width() - a[0]) : e.offsetTop = t.offset), t.fixed || (e.offsetTop = /%$/.test(e.offsetTop) ? n.height() * parseFloat(e.offsetTop) / 100 : parseFloat(e.offsetTop), e.offsetLeft = /%$/.test(e.offsetLeft) ? n.width() * parseFloat(e.offsetLeft) / 100 : parseFloat(e.offsetLeft), e.offsetTop += n.scrollTop(), e.offsetLeft += n.scrollLeft()), i.attr("minLeft") && (e.offsetTop = n.height() - (i.find(s[1]).outerHeight() || 0), e.offsetLeft = i.css("left")), i.css({
            top: e.offsetTop,
            left: e.offsetLeft
        })
    }, l.pt.tips = function () {
        var e = this, t = e.config, a = e.layero, o = [a.outerWidth(), a.outerHeight()], r = i(t.follow);
        r[0] || (r = i("body"));
        var l = {width: r.outerWidth(), height: r.outerHeight(), top: r.offset().top, left: r.offset().left},
            f = a.find(".layui-layer-TipsG"), c = t.tips[0];
        t.tips[1] || f.remove(), l.autoLeft = function () {
            l.left + o[0] - n.width() > 0 ? (l.tipLeft = l.left + l.width - o[0], f.css({
                right: 12,
                left: "auto"
            })) : l.tipLeft = l.left
        }, l.where = [function () {
            l.autoLeft(), l.tipTop = l.top - o[1] - 10, f.removeClass("layui-layer-TipsB").addClass("layui-layer-TipsT").css("border-right-color", t.tips[1])
        }, function () {
            l.tipLeft = l.left + l.width + 10, l.tipTop = l.top, f.removeClass("layui-layer-TipsL").addClass("layui-layer-TipsR").css("border-bottom-color", t.tips[1])
        }, function () {
            l.autoLeft(), l.tipTop = l.top + l.height + 10, f.removeClass("layui-layer-TipsT").addClass("layui-layer-TipsB").css("border-right-color", t.tips[1])
        }, function () {
            l.tipLeft = l.left - o[0] - 10, l.tipTop = l.top, f.removeClass("layui-layer-TipsR").addClass("layui-layer-TipsL").css("border-bottom-color", t.tips[1])
        }], l.where[c - 1](), 1 === c ? l.top - (n.scrollTop() + o[1] + 16) < 0 && l.where[2]() : 2 === c ? n.width() - (l.left + l.width + o[0] + 16) > 0 || l.where[3]() : 3 === c ? l.top - n.scrollTop() + l.height + o[1] + 16 - n.height() > 0 && l.where[0]() : 4 === c && o[0] + 16 - l.left > 0 && l.where[1](), a.find("." + s[5]).css({
            "background-color": t.tips[1],
            "padding-right": t.closeBtn ? "30px" : ""
        }), a.css({left: l.tipLeft - (t.fixed ? n.scrollLeft() : 0), top: l.tipTop - (t.fixed ? n.scrollTop() : 0)})
    }, l.pt.move = function () {
        var e = this, t = e.config, a = i(document), l = e.layero, s = l.find(t.move),
            f = l.find(".layui-layer-resize"), c = {};
        return t.move && s.css("cursor", "move"), s.on("mousedown", function (e) {
            e.preventDefault(), t.move && (c.moveStart = !0, c.offset = [e.clientX - parseFloat(l.css("left")), e.clientY - parseFloat(l.css("top"))], o.moveElem.css("cursor", "move").show())
        }), f.on("mousedown", function (e) {
            e.preventDefault(), c.resizeStart = !0, c.offset = [e.clientX, e.clientY], c.area = [l.outerWidth(), l.outerHeight()], o.moveElem.css("cursor", "se-resize").show()
        }), a.on("mousemove", function (i) {
            if (c.moveStart) {
                var a = i.clientX - c.offset[0], o = i.clientY - c.offset[1], s = "fixed" === l.css("position");
                if (i.preventDefault(), c.stX = s ? 0 : n.scrollLeft(), c.stY = s ? 0 : n.scrollTop(), !t.moveOut) {
                    var f = n.width() - l.outerWidth() + c.stX, d = n.height() - l.outerHeight() + c.stY;
                    a < c.stX && (a = c.stX), a > f && (a = f), o < c.stY && (o = c.stY), o > d && (o = d)
                }
                l.css({left: a, top: o})
            }
            if (t.resize && c.resizeStart) {
                var a = i.clientX - c.offset[0], o = i.clientY - c.offset[1];
                i.preventDefault(), r.style(e.index, {width: c.area[0] + a, height: c.area[1] + o}), c.isResize = !0
            }
        }).on("mouseup", function (e) {
            c.moveStart && (delete c.moveStart, o.moveElem.hide(), t.moveEnd && t.moveEnd()), c.resizeStart && (delete c.resizeStart, o.moveElem.hide())
        }), e
    }, l.pt.callback = function () {
        function e() {
            var e = a.cancel && a.cancel(t.index, n);
            e === !1 || r.close(t.index)
        }

        var t = this, n = t.layero, a = t.config;
        t.openLayer(), a.success && (2 == a.type ? n.find("iframe").on("load", function () {
            a.success(n, t.index)
        }) : a.success(n, t.index)), 6 == r.ie && t.IE6(n), n.find("." + s[6]).children("a").on("click", function () {
            var e = i(this).index();
            if (0 === e) a.yes ? a.yes(t.index, n) : a.btn1 ? a.btn1(t.index, n) : r.close(t.index); else {
                var o = a["btn" + (e + 1)] && a["btn" + (e + 1)](t.index, n);
                o === !1 || r.close(t.index)
            }
        }), n.find("." + s[7]).on("click", e), a.shadeClose && i("#layui-layer-shade" + t.index).on("click", function () {
            r.close(t.index)
        }), n.find(".layui-layer-min").on("click", function () {
            var e = a.min && a.min(n);
            e === !1 || r.min(t.index, a)
        }), n.find(".layui-layer-max").on("click", function () {
            i(this).hasClass("layui-layer-maxmin") ? (r.restore(t.index), a.restore && a.restore(n)) : (r.full(t.index, a), setTimeout(function () {
                a.full && a.full(n)
            }, 100))
        }), a.end && (o.end[t.index] = a.end)
    }, o.reselect = function () {
        i.each(i("select"), function (e, t) {
            var n = i(this);
            n.parents("." + s[0])[0] || 1 == n.attr("layer") && i("." + s[0]).length < 1 && n.removeAttr("layer").show(), n = null
        })
    }, l.pt.IE6 = function (e) {
        i("select").each(function (e, t) {
            var n = i(this);
            n.parents("." + s[0])[0] || "none" === n.css("display") || n.attr({layer: "1"}).hide(), n = null
        })
    }, l.pt.openLayer = function () {
        var e = this;
        r.zIndex = e.config.zIndex, r.setTop = function (e) {
            var t = function () {
                r.zIndex++, e.css("z-index", r.zIndex + 1)
            };
            return r.zIndex = parseInt(e[0].style.zIndex), e.on("mousedown", t), r.zIndex
        }
    }, o.record = function (e) {
        var t = [e.width(), e.height(), e.position().top, e.position().left + parseFloat(e.css("margin-left"))];
        e.find(".layui-layer-max").addClass("layui-layer-maxmin"), e.attr({area: t})
    }, o.rescollbar = function (e) {
        s.html.attr("layer-full") == e && (s.html[0].style.removeProperty ? s.html[0].style.removeProperty("overflow") : s.html[0].style.removeAttribute("overflow"), s.html.removeAttr("layer-full"))
    }, e.layer = r, r.getChildFrame = function (e, t) {
        return t = t || i("." + s[4]).attr("times"), i("#" + s[0] + t).find("iframe").contents().find(e)
    }, r.getFrameIndex = function (e) {
        return i("#" + e).parents("." + s[4]).attr("times")
    }, r.iframeAuto = function (e) {
        if (e) {
            var t = r.getChildFrame("html", e).outerHeight(), n = i("#" + s[0] + e),
                a = n.find(s[1]).outerHeight() || 0, o = n.find("." + s[6]).outerHeight() || 0;
            n.css({height: t + a + o}), n.find("iframe").css({height: t})
        }
    }, r.iframeSrc = function (e, t) {
        i("#" + s[0] + e).find("iframe").attr("src", t)
    }, r.style = function (e, t, n) {
        var a = i("#" + s[0] + e), r = a.find(".layui-layer-content"), l = a.attr("type"),
            f = a.find(s[1]).outerHeight() || 0, c = a.find("." + s[6]).outerHeight() || 0;
        a.attr("minLeft");
        l !== o.type[3] && l !== o.type[4] && (n || (parseFloat(t.width) <= 260 && (t.width = 260), parseFloat(t.height) - f - c <= 64 && (t.height = 64 + f + c)), a.css(t), c = a.find("." + s[6]).outerHeight(), l === o.type[2] ? a.find("iframe").css({height: parseFloat(t.height) - f - c}) : r.css({height: parseFloat(t.height) - f - c - parseFloat(r.css("padding-top")) - parseFloat(r.css("padding-bottom"))}))
    }, r.min = function (e, t) {
        var a = i("#" + s[0] + e), l = a.find(s[1]).outerHeight() || 0,
            f = a.attr("minLeft") || 181 * o.minIndex + "px", c = a.css("position");
        o.record(a), o.minLeft[0] && (f = o.minLeft[0], o.minLeft.shift()), a.attr("position", c), r.style(e, {
            width: 180,
            height: l,
            left: f,
            top: n.height() - l,
            position: "fixed",
            overflow: "hidden"
        }, !0), a.find(".layui-layer-min").hide(), "page" === a.attr("type") && a.find(s[4]).hide(), o.rescollbar(e), a.attr("minLeft") || o.minIndex++, a.attr("minLeft", f)
    }, r.restore = function (e) {
        var t = i("#" + s[0] + e), n = t.attr("area").split(",");
        t.attr("type");
        r.style(e, {
            width: parseFloat(n[0]),
            height: parseFloat(n[1]),
            top: parseFloat(n[2]),
            left: parseFloat(n[3]),
            position: t.attr("position"),
            overflow: "visible"
        }, !0), t.find(".layui-layer-max").removeClass("layui-layer-maxmin"), t.find(".layui-layer-min").show(), "page" === t.attr("type") && t.find(s[4]).show(), o.rescollbar(e)
    }, r.full = function (e) {
        var t, a = i("#" + s[0] + e);
        o.record(a), s.html.attr("layer-full") || s.html.css("overflow", "hidden").attr("layer-full", e), clearTimeout(t), t = setTimeout(function () {
            var t = "fixed" === a.css("position");
            r.style(e, {
                top: t ? 0 : n.scrollTop(),
                left: t ? 0 : n.scrollLeft(),
                width: n.width(),
                height: n.height()
            }, !0), a.find(".layui-layer-min").hide()
        }, 100)
    }, r.title = function (e, t) {
        var n = i("#" + s[0] + (t || r.index)).find(s[1]);
        n.html(e)
    }, r.close = function (e) {
        var t = i("#" + s[0] + e), n = t.attr("type"), a = "layer-anim-close";
        if (t[0]) {
            var l = "layui-layer-wrap", f = function () {
                if (n === o.type[1] && "object" === t.attr("conType")) {
                    t.children(":not(." + s[5] + ")").remove();
                    for (var a = t.find("." + l), r = 0; r < 2; r++) a.unwrap();
                    a.css("display", a.data("display")).removeClass(l)
                } else {
                    if (n === o.type[2]) try {
                        var f = i("#" + s[4] + e)[0];
                        f.contentWindow.document.write(""), f.contentWindow.close(), t.find("." + s[5])[0].removeChild(f)
                    } catch (c) {
                    }
                    t[0].innerHTML = "", t.remove()
                }
                "function" == typeof o.end[e] && o.end[e](), delete o.end[e]
            };
            t.data("anim") && t.addClass(a), i("#layui-layer-moves, #layui-layer-shade" + e).remove(), 6 == r.ie && o.reselect(), o.rescollbar(e), t.attr("minLeft") && (o.minIndex--, o.minLeft.push(t.attr("minLeft"))), setTimeout(function () {
                f()
            }, r.ie && r.ie < 10 || !t.data("anim") ? 0 : 200)
        }
    }, r.closeAll = function (e) {
        i.each(i("." + s[0]), function () {
            var t = i(this), n = e ? t.attr("type") === e : 1;
            n && r.close(t.attr("times")), n = null
        })
    };
    var f = r.cache || {}, c = function (e) {
        return f.skin ? " " + f.skin + " " + f.skin + "-" + e : ""
    };
    r.prompt = function (e, t) {
        var a = "";
        if (e = e || {}, "function" == typeof e && (t = e), e.area) {
            var o = e.area;
            a = 'style="width: ' + o[0] + "; height: " + o[1] + ';"', delete e.area
        }
        var l,
            s = 2 == e.formType ? '<textarea class="layui-layer-input"' + a + ">" + (e.value || "") + "</textarea>" : function () {
                return '<input type="' + (1 == e.formType ? "password" : "text") + '" class="layui-layer-input" value="' + (e.value || "") + '">'
            }();
        return r.open(i.extend({
            type: 1,
            btn: ["&#x786E;&#x5B9A;", "&#x53D6;&#x6D88;"],
            content: s,
            skin: "layui-layer-prompt" + c("prompt"),
            maxWidth: n.width(),
            success: function (e) {
                l = e.find(".layui-layer-input"), l.focus()
            },
            resize: !1,
            yes: function (i) {
                var n = l.val();
                "" === n ? l.focus() : n.length > (e.maxlength || 500) ? r.tips("&#x6700;&#x591A;&#x8F93;&#x5165;" + (e.maxlength || 500) + "&#x4E2A;&#x5B57;&#x6570;", l, {tips: 1}) : t && t(n, i, l)
            }
        }, e))
    }, r.tab = function (e) {
        e = e || {};
        var t = e.tab || {};
        return r.open(i.extend({
            type: 1, skin: "layui-layer-tab" + c("tab"), resize: !1, title: function () {
                var e = t.length, i = 1, n = "";
                if (e > 0) for (n = '<span class="layui-layer-tabnow">' + t[0].title + "</span>"; i < e; i++) n += "<span>" + t[i].title + "</span>";
                return n
            }(), content: '<ul class="layui-layer-tabmain">' + function () {
                var e = t.length, i = 1, n = "";
                if (e > 0) for (n = '<li class="layui-layer-tabli xubox_tab_layer">' + (t[0].content || "no content") + "</li>"; i < e; i++) n += '<li class="layui-layer-tabli">' + (t[i].content || "no  content") + "</li>";
                return n
            }() + "</ul>", success: function (t) {
                var n = t.find(".layui-layer-title").children(), a = t.find(".layui-layer-tabmain").children();
                n.on("mousedown", function (t) {
                    t.stopPropagation ? t.stopPropagation() : t.cancelBubble = !0;
                    var n = i(this), o = n.index();
                    n.addClass("layui-layer-tabnow").siblings().removeClass("layui-layer-tabnow"), a.eq(o).show().siblings().hide(), "function" == typeof e.change && e.change(o)
                })
            }
        }, e))
    }, r.photos = function (t, n, a) {
        function o(e, t, i) {
            var n = new Image;
            return n.src = e, n.complete ? t(n) : (n.onload = function () {
                n.onload = null, t(n)
            }, void(n.onerror = function (e) {
                n.onerror = null, i(e)
            }))
        }

        var l = {};
        if (t = t || {}, t.photos) {
            var s = t.photos.constructor === Object, f = s ? t.photos : {}, d = f.data || [], u = f.start || 0;
            if (l.imgIndex = (0 | u) + 1, t.img = t.img || "img", s) {
                if (0 === d.length) return r.msg("&#x6CA1;&#x6709;&#x56FE;&#x7247;")
            } else {
                var y = i(t.photos), p = function () {
                    d = [], y.find(t.img).each(function (e) {
                        var t = i(this);
                        t.attr("layer-index", e), d.push({
                            alt: t.attr("alt"),
                            pid: t.attr("layer-pid"),
                            src: t.attr("layer-src") || t.attr("src"),
                            thumb: t.attr("src")
                        })
                    })
                };
                if (p(), 0 === d.length) return;
                if (n || y.on("click", t.img, function () {
                    var e = i(this), n = e.attr("layer-index");
                    r.photos(i.extend(t, {photos: {start: n, data: d, tab: t.tab}, full: t.full}), !0), p()
                }), !n) return
            }
            l.imgprev = function (e) {
                l.imgIndex--, l.imgIndex < 1 && (l.imgIndex = d.length), l.tabimg(e)
            }, l.imgnext = function (e, t) {
                l.imgIndex++, l.imgIndex > d.length && (l.imgIndex = 1, t) || l.tabimg(e)
            }, l.keyup = function (e) {
                if (!l.end) {
                    var t = e.keyCode;
                    e.preventDefault(), 37 === t ? l.imgprev(!0) : 39 === t ? l.imgnext(!0) : 27 === t && r.close(l.index)
                }
            }, l.tabimg = function (e) {
                d.length <= 1 || (f.start = l.imgIndex - 1, r.close(l.index), r.photos(t, !0, e))
            }, l.event = function () {
                l.bigimg.hover(function () {
                    l.imgsee.show()
                }, function () {
                    l.imgsee.hide()
                }), l.bigimg.find(".layui-layer-imgprev").on("click", function (e) {
                    e.preventDefault(), l.imgprev()
                }), l.bigimg.find(".layui-layer-imgnext").on("click", function (e) {
                    e.preventDefault(), l.imgnext()
                }), i(document).on("keyup", l.keyup)
            }, l.loadi = r.load(1, {shade: !("shade" in t) && .9, scrollbar: !1}), o(d[u].src, function (n) {
                r.close(l.loadi), l.index = r.open(i.extend({
                    type: 1,
                    area: function () {
                        var a = [n.width, n.height], o = [i(e).width() - 100, i(e).height() - 100];
                        if (!t.full && (a[0] > o[0] || a[1] > o[1])) {
                            var r = [a[0] / o[0], a[1] / o[1]];
                            r[0] > r[1] ? (a[0] = a[0] / r[0], a[1] = a[1] / r[0]) : r[0] < r[1] && (a[0] = a[0] / r[1], a[1] = a[1] / r[1])
                        }
                        return [a[0] + "px", a[1] + "px"]
                    }(),
                    title: !1,
                    shade: .9,
                    shadeClose: !0,
                    closeBtn: !1,
                    move: ".layui-layer-phimg img",
                    moveType: 1,
                    scrollbar: !1,
                    moveOut: !0,
                    anim: 5 * Math.random() | 0,
                    skin: "layui-layer-photos" + c("photos"),
                    content: '<div class="layui-layer-phimg"><img src="' + d[u].src + '" alt="' + (d[u].alt || "") + '" layer-pid="' + d[u].pid + '"><div class="layui-layer-imgsee">' + (d.length > 1 ? '<span class="layui-layer-imguide"><a href="javascript:;" class="layui-layer-iconext layui-layer-imgprev"></a><a href="javascript:;" class="layui-layer-iconext layui-layer-imgnext"></a></span>' : "") + '<div class="layui-layer-imgbar" style="display:' + (a ? "block" : "") + '"><span class="layui-layer-imgtit"><a href="javascript:;">' + (d[u].alt || "") + "</a><em>" + l.imgIndex + "/" + d.length + "</em></span></div></div></div>",
                    success: function (e, i) {
                        l.bigimg = e.find(".layui-layer-phimg"), l.imgsee = e.find(".layui-layer-imguide,.layui-layer-imgbar"), l.event(e), t.tab && t.tab(d[u], e)
                    },
                    end: function () {
                        l.end = !0, i(document).off("keyup", l.keyup)
                    }
                }, t))
            }, function () {
                r.close(l.loadi), r.msg("&#x5F53;&#x524D;&#x56FE;&#x7247;&#x5730;&#x5740;&#x5F02;&#x5E38;<br>&#x662F;&#x5426;&#x7EE7;&#x7EED;&#x67E5;&#x770B;&#x4E0B;&#x4E00;&#x5F20;&#xFF1F;", {
                    time: 3e4,
                    btn: ["&#x4E0B;&#x4E00;&#x5F20;", "&#x4E0D;&#x770B;&#x4E86;"],
                    yes: function () {
                        d.length > 1 && l.imgnext(!0, !0)
                    }
                })
            })
        }
    }, o.run = function (t) {
        i = t, n = i(e), s.html = i("html"), r.open = function (e) {
            var t = new l(e);
            return t.index
        }
    }, e.layui && layui.define ? (r.ready(), layui.define("jquery", function (t) {
        r.path = layui.cache.dir, o.run(layui.jquery), e.layer = r, t("layer", r)
    })) : "function" == typeof define ? define(["jquery.min"], function () {
        return o.run(e.jQuery), r
    }) : function () {
        o.run(e.jQuery), r.ready()
    }()
}(window);