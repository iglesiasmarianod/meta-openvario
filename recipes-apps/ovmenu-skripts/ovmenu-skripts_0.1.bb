# Copyright (C) 2014 Unknow User <unknow@user.org>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Update skripts (eg. Maps, Airspaces, ...)"
HOMEPAGE = ""
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=c79ff39f19dfec6d293b95dea7b07891"
SECTION = "base/app"

S = "${WORKDIR}"
PR = "r3"

RDEPENDS_${PN} = "bash autofs autofs-config"

SRC_URI =      "\
	file://update-maps.sh \
	file://update-system.sh \
	file://download-igc.sh \
"


addtask do_package_write_ipk after do_package after do_install

do_compile() {
        :
}

do_install() {
        echo "Installing ..."
        install -d ${D}/usr/bin
        install -m 0755 ${S}/update-maps.sh ${D}/usr/bin/update-maps.sh
	install -m 0755 ${S}/update-system.sh ${D}/usr/bin/update-system.sh
	install -m 0755 ${S}/download-igc.sh ${D}/usr/bin/download-igc.sh
}

FILES_${PN} = "/usr/bin/update-maps.sh \
	       /usr/bin/update-system.sh \
	       /usr/bin/download-igc.sh \
"
