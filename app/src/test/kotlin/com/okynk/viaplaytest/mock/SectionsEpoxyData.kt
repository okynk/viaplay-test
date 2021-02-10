package com.okynk.viaplaytest.mock

import com.okynk.viaplaytest.feature.screen.sections.epoxy.SectionsEpoxyData

val mockSectionsEpoxyData_1 = SectionsEpoxyData(
    id = mockLinkEntity_1.id,
    title = mockLinkEntity_1.title,
    item = mockLinkEntity_1
)

val mockSectionsEpoxyData_2 = SectionsEpoxyData(
    id = mockLinkEntity_2.id,
    title = mockLinkEntity_2.title,
    item = mockLinkEntity_2
)

val mockSectionsEpoxyData_list = listOf(
    mockSectionsEpoxyData_1,
    mockSectionsEpoxyData_2
)