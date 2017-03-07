!(function(global, factory) {
    factory(global);
}(this, function(global) {
    'use strict';
    var win = window;
    var doc = document;
    var docEl = doc.documentElement;

    var defaults = {
        width: 500,
        height: 400,
        offsetX: 0,
        offsetY: 0,
        zIndex: 999,
        closeButtonClass: 'popup-close'
    };

    var extend = function(to, from) {
        for (var p in from)
            if (from.hasOwnProperty(p)) { to[p] = from[p]; }
        return to;
    };

    var winW = function() { return win.innerWidth || docEl.clientWidth; };
    var winH = function() { return win.innerHeight || docEl.clientHeight; };
    var scrollX = function() { return win.pageXOffset || docEl.scrollLeft; };
    var scrollY = function() { return win.pageYOffset || docEl.scrollTop; };

    var byClass = function(cls, el) {
        el = el || doc;
        if (el.getElementsByClassName) { return el.getElementsByClassName(cls); }
        else {
            var ret = [];
            var re = new RegExp('^|\\s+' + cls + '\\s+|$');
            var tags = el.getElementsByTagName('*');
            for (var i = 0, len = tags.length; i < len; i++) {
                if (re.test(tags[i].className)) {
                    ret.push(tags[i]);
                }
            }
            return ret;
        }
    };

    var bind = function(el, type, listener) {
        if (el.addEventListener) {
            el.addEventListener(type, listener, false);
        }
        else {
            el.attachEvent('on' + type, listener);
        }
    };

    function Popup(el, opts) {
        if (!(this instanceof Popup)) {
            return new Popup(el, opts);
        }
        this.opts = extend({}, extend(defaults, opts));
        this.el = el;
        this.init();
    }

    extend(Popup.prototype, {
        init: function() {
            var opts = this.opts;
            extend(this.el.style, {
                position: 'absolute',
                width: opts.width + 'px',
                height: opts.height + 'px',
                zIndex: opts.zIndex
            });
            this.bindEvent();
        },

        bindEvent: function() {
            var closeButton = byClass(this.opts.closeButtonClass)[0];
            var self = this;

            bind(closeButton, 'click', function() { self.close(); });

            bind(doc, 'keydown', function(e) {
                e = e || window.event;
                var keyCode = e.which || e.keyCode;
                if (keyCode === 27)  self.close();
            });

            bind(win, 'resize', function() {
                self.setPosition();
            });
        },

        open: function() {
            this.el.style.display = 'block';
            this.setPosition();
            var self = this;
            setTimeout(function () {
                self.close();
            }, 4500);
        },

        close: function() {
            this.el.style.display = 'none';
        },

        setPosition: function() {
            var opts = this.opts;
            var top = scrollY() + Math.max(0, (winH() - opts.height) / 2);
            var left = scrollX() + Math.max(0, (winW() - opts.width) / 2);

            extend(this.el.style, {
                top: top + opts.offsetY + 'px',
                left: left + opts.offsetX + 'px'
            });
        },

        setMessage: function(message){ document.getElementById('pid').innerHTML = message; }
    });

    if (global) { global.Popup = Popup; }
    return Popup;
}));