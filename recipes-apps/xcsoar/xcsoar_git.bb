# Copyright (C) 2014 Unknow User <unknow@user.org>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "XCSoar glide computer"
HOMEPAGE = "www.xcsoar.org"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"
SECTION = "base/app"
DEPENDS = "	libsdl \
		jpeg \
		freetype \
		libpng \
		libinput \
		boost \
		ttf-dejavu \
		sunxi-mali \
		curlpp \
		alsa-lib \
"

RDEPENDS_${PN} = "	sunxi-mali \
			libinput \
			libsdl \
			udev \
			alsa-lib \
"

S = "${WORKDIR}/git"
PR = "r1"

SRCREV_pn-xcsoar = "${AUTOREV}" 

SRC_URI = 	"git://git-ro.openvario.org/xcsoar.git;protocol=http \
				 file://0002-Adapted-toolchain-prefixes-for-cross-compile.patch \
				 file://0001-Adapted-Flags-for-compiler-and-linker-for-cross-comp.patch \
				 file://0001-Disable-warnings-as-errors.patch \
				 file://0001-Override-detection-of-target-hardware.patch \
"

addtask do_package_write_ipk after do_package after do_install

do_compile() {
	echo $CC
	$CC --version
	echo "Making .."
	echo '${WORKDIR}'
	cd ${WORKDIR}/git
	make -j8 DEBUG=n DEBUG_GLIBCXX=n ENABLE_ALSA=y
}

do_install() {
	echo "Installing ..."
	install -d ${D}/opt/XCSoar/bin
	install -m 0755 ${S}/output/UNIX/bin/* ${D}/opt/XCSoar/bin

}

FILES_${PN} = " \
	/opt/XCSoar/bin/* \
"

