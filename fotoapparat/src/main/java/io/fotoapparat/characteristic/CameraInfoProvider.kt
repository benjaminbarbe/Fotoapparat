@file:Suppress("DEPRECATION")

package io.fotoapparat.characteristic

import android.hardware.Camera
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Build
import io.fotoapparat.hardware.orientation.toOrientation

/**
 * Returns the [Characteristics] for the given `cameraId`.
 */
internal fun getCharacteristics(cameraId: Int, cameraManager: CameraManager?): Characteristics {
    val info = Camera.CameraInfo()
    Camera.getCameraInfo(cameraId, info)
    val lensPosition = info.facing.toLensPosition()
    return Characteristics(
            cameraId = cameraId,
            lensPosition = lensPosition,
            cameraOrientation = info.orientation.toOrientation(),
            isMirrored = lensPosition == LensPosition.Front
    )
}
