SUMMARY = "config files and scripts for a guest"
DESCRIPTION = "config files and scripts for guest which will be running for tests"

require inc/xt_shared_env.inc

PV = "0.1"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = " \
    file://bridge-nfsroot.sh \
    file://bridge.sh \
    file://displbe.service \
    file://bridge-up-notification.service \
    file://display-manager.service \
    file://dm-salvator-x-m3.cfg \
    file://dm-salvator-x-h3.cfg \
    file://dm-ulcb.cfg \
    file://dm-salvator-xs-m3n.cfg \
    file://eth0.network \
    file://xenbr0.netdev \
    file://xenbr0.network \
    file://xenbr0-systemd-networkd.conf \
    file://port-forward-systemd-networkd.conf \
    file://sndbe.service \
    file://sata_en.sh \
"

S = "${WORKDIR}"

inherit systemd

PACKAGES += " \
    ${PN}-bridge-config \
    ${PN}-displbe-service \
    ${PN}-sndbe-service \
    ${PN}-bridge-up-notification-service \
"

FILES_${PN}-bridge-config = " \
    ${sysconfdir}/systemd/network/eth0.network \
    ${sysconfdir}/systemd/network/xenbr0.netdev \
    ${sysconfdir}/systemd/network/xenbr0.network \
    ${sysconfdir}/systemd/system/systemd-networkd.service.d/xenbr0-systemd-networkd.conf \
    ${sysconfdir}/systemd/system/systemd-networkd.service.d/port-forward-systemd-networkd.conf \
"

SYSTEMD_PACKAGES = " \
    ${PN}-displbe-service \
    ${PN}-sndbe-service \
    ${PN}-bridge-up-notification-service \
"

SYSTEMD_SERVICE_${PN}-displbe-service = " displbe.service"

SYSTEMD_SERVICE_${PN}-sndbe-service = " sndbe.service"

SYSTEMD_SERVICE_${PN}-bridge-up-notification-service = " bridge-up-notification.service"

FILES_${PN}-displbe-service = " \
    ${systemd_system_unitdir}/displbe.service \
"

FILES_${PN}-sndlbe-service = " \
    ${systemd_system_unitdir}/sndbe.service \
"

FILES_${PN}-bridge-up-notification-service = " \
    ${systemd_system_unitdir}/bridge-up-notification.service \
"
RDEPENDS_${PN}-bridge-config = " \
    ethtool \
"

DM_CONFIG_salvator-x-m3-xt = "dm-salvator-x-m3.cfg"
DM_CONFIG_salvator-x-h3-xt = "dm-salvator-x-h3.cfg"
DM_CONFIG_salvator-xs-h3-xt = "dm-salvator-x-h3.cfg"
DM_CONFIG_salvator-xs-h3-4x2g-xt = "dm-salvator-x-h3.cfg"
DM_CONFIG_salvator-x-h3-4x2g-xt = "dm-salvator-x-h3.cfg"
DM_CONFIG_ulcb = "dm-ulcb.cfg"
DM_CONFIG_salvator-xs-m3n-xt = "dm-salvator-xs-m3n.cfg"

do_install() {
    install -d ${D}${base_prefix}${XT_DIR_ABS_ROOTFS_SCRIPTS}
    install -m 0744 ${WORKDIR}/*.sh ${D}${base_prefix}${XT_DIR_ABS_ROOTFS_SCRIPTS}

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/displbe.service ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/sndbe.service ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/bridge-up-notification.service ${D}${systemd_system_unitdir}

    install -d ${D}${sysconfdir}/systemd/network/
    install -m 0644 ${WORKDIR}/*.network ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/*.netdev ${D}${sysconfdir}/systemd/network

    install -d ${D}${sysconfdir}/systemd/system/systemd-networkd.service.d
    install -m 0644 ${WORKDIR}/xenbr0-systemd-networkd.conf ${D}${sysconfdir}/systemd/system/systemd-networkd.service.d
    install -m 0644 ${WORKDIR}/port-forward-systemd-networkd.conf ${D}${sysconfdir}/systemd/system/systemd-networkd.service.d
}

FILES_${PN} = " \
    ${base_prefix}${XT_DIR_ABS_ROOTFS_SCRIPTS}/*.sh \
"

